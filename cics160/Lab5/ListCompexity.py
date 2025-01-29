import time
import math


start_time = time.time()
x=math.sqrt(3.14159)
end_time = time.time()
print(f"That calculation took {end_time-start_time}")


cpu_start_time = time.process_time()
x=math.sqrt(3.14159)
cpu_end_time = time.process_time()
print(f"That calculation (cpu) took {cpu_end_time-cpu_start_time}")


start_time = time.time()
num_list = []
for num in range(200000):
 if num%10000==0:
   print(time.time()-start_time)
 num_list.insert(0, num)
end_time = time.time()
print(f"Inserting to a list took {end_time-start_time}")


cpu_start_time = time.process_time()
num_list = []
for num in range(200000):
 if num%10000==0:
   print(time.process_time()-cpu_start_time)
 num_list.insert(0,num)
cpu_end_time = time.process_time()
print(f"Inserting to a list (cpu) took {cpu_end_time-cpu_start_time}")

# What is the complexity of inserting something into a list?
# It would take O(n) time to insert something because, in the worst case scenario, inserting something at position 0 requires pushing everything else in the list down one space, which involves iterating through the list. Thus, the insert operation is a linear function of the length of the list, which is O(n) complexity.

#inserting took a significantly longer time than appending because it is moving every element by space every time
#the more elements in the list, the longer it takes
