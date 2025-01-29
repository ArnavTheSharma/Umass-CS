import time
import math

def ask_user_for_number():
 return_list = []
 for i in range(10):
   return_list.append(input("Pick a number: "))



start_time = time.time()
ask_user_for_number()
end_time = time.time()
print(f"Appending to a list took {end_time-start_time}")


cpu_start_time = time.process_time()
ask_user_for_number()
cpu_end_time = time.process_time()
print(f"Appending to a list (cpu) took {cpu_end_time-cpu_start_time}")

#Cpu_time does not count the time it takes while waiting for the user input, while the clock time does.
#As a result, CPU time takes far shorter than clock time
