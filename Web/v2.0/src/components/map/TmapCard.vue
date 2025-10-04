<template>
    <tmap-map
        :mapKey="KEY"
        :events="events"
        :center="center"
        :zoom="zoom"
        :doubleClickZoom="doubleClickZoom"
        :control="control"
    >
        <tmap-multi-marker
            :styles="light_param.markerStyles"
            :geometries="light_param.markerGeometries"
            @click="light_click"
            ref="markers"
        />
    </tmap-map>
</template>

<script lang="ts">
import {defineComponent, onMounted, reactive, ref} from 'vue';
import {T_KEY,ICON_LIGHT,ICON_LIGHT_2,LIGHT_SITES} from '../../api/mapApi'

/*地图路灯点击事件*/
const light_click = (key)=>{
    console.log(key.geometry.id);
    console.log('light clicked');
}
/*路灯图标数据*/
const light_param = {
    /*路灯图标变量*/
    markerStyles: {
        normal: {
            width: 35,
            height: 35,
            anchor: { x: 16, y: 32 },
            src: ICON_LIGHT_2,
        }
    },
    markerGeometries: [
        /*路灯坐标*/
        /*坐标示例
        {
            id: 'start',
            styleId: 'start',
            position: { lat: 41.726954, lng: 123.492468 },
        },
         */
    ],
}

export default defineComponent({
    name: 'Home',
    setup() {
        const KEY = T_KEY
        const center = ref({ lat: 41.727896, lng: 123.493964 });
        const zoom = ref(16);
        const doubleClickZoom = ref(true);
        const print = (e: unknown) => {
            console.log(e);
        };

        onMounted(()=>{
            LIGHT_SITES().then(data=>{
                for (let dataKey in data) {
                    const site = {
                        id: data[dataKey],
                        styleId: 'normal',
                        position: { lat: data[dataKey].lat, lng: data[dataKey].lng },
                    }
                    console.log(site);
                    light_param.markerGeometries.push(site)
                }
            })
        })

        return {
            events: {
                dblclick: print,
            },
            KEY,
            center,
            zoom,
            doubleClickZoom,
            control: {
                scale: {},
                zoom: {
                    position: 'bottomRight',
                },
            },
            light_param,
            light_click
        };
    },
});
</script>