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
import {defineComponent, reactive, ref} from 'vue';
import {T_KEY,ICON_LIGHT,ICON_LIGHT_2} from '../../api/mapApi'

/*地图路灯点击事件*/
const light_click = ()=>{
    console.log('light clicked');
}
/*路灯图标数据*/
const light_param = {
    /*路灯图标变量*/
    markerStyles: {
        start: {
            width: 35,
            height: 35,
            anchor: { x: 16, y: 32 },
            src: ICON_LIGHT,
        },
        end: {
            width: 35,
            height: 35,
            anchor: { x: 16, y: 32 },
            src: ICON_LIGHT_2,
        },
    },
    markerGeometries: [
        {
            id: 'start',
            styleId: 'start',
            position: { lat: 41.726954, lng: 123.492468 },
        },
        {
            id: 'end',
            styleId: 'end',
            position: { lat: 41.729304, lng: 123.498578 },
        },
    ],
}

export default defineComponent({
    name: 'Home',
    setup() {
        const KEY = T_KEY
        const center = ref({ lat: 41.727896, lng: 123.493964 });
        const zoom = ref(15);
        const doubleClickZoom = ref(true);
        const print = (e: unknown) => {
            console.log(e);
        };

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