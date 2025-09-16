#ifndef __THREAD_H
#define __THREAD_H


void light_task(void* args);

void sensor_task(void* args);

void load_task(void* args);

void update_task(void* args);



#endif // !__THREAD_H