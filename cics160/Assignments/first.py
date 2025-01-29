
def reverse_list():
    num_list = []
    n = int(input("How many numbers do you wanna input? "))
    for i in range(0, n):
        m = int(input("Enter a number to store in a list: "))
        num_list.append(m)
    
    return num_list[::-1]


def is_older():
    name1 = (input("name of first person: "))
    name2 = (input("name of second person: "))
    age1 = int(input("Age of first person: "))
    age2 = int(input("Age of second person: "))

    if age1 == age2:
        return (f"{name1} and {name2} are the same age, {age1}")
    elif age1 > age2:
        return (f"{name1} is older than {name2} by {age1-age2} year(s)")
    else:
        return (f"{name2} is older than {name1} by {age2-age1} year(s)")

print(is_older())
