import time
import math


def nums_to_10000_in_list():
    num_list = []
    for num in range(10000):
        num_list.append(num)
    return num_list
 


start_time = time.time()
x=math.sqrt(3.14159)
end_time = time.time()
print(f"That calculation took {end_time-start_time}")


cpu_start_time = time.process_time()
x=math.sqrt(3.14159)
cpu_end_time = time.process_time()
print(f"That calculation (cpu) took {cpu_end_time-cpu_start_time}")


start_time = time.time()
nums_to_10000_in_list()
end_time = time.time()
print(f"Appending to a list took {end_time-start_time}")


cpu_start_time = time.process_time()
nums_to_10000_in_list()
cpu_end_time = time.process_time()
print(f"Appending to a list (cpu) took {cpu_end_time-cpu_start_time}")


#generally, the CPU takes less time to do the functions than real time, but there is some variation
