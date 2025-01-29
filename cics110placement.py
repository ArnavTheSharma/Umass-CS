def function1(list):
    bool = True
    for i in range(0, len(list)):
        if list[i] == list[i+1]:
            bool = False
            break
        elif list[i] > list[i+1]:
            for j in range(i, len(list)):
                if list[j] < list[j+1]:
                    bool = False
                    break
        else:
            continue
    return bool


def function2(list):
    list2 = list.sorted()
    for i in range(0, len(list), -1):
        if list(i) == list2(0):
            return i
        

def function3(list1, list2):
    list3 = []
    for i in list1:
        for j in list2:
            if i == j:
                list3.append(i)
    return list3
# now realizing this may add duplicates, should've used a set instead of a list
