with open("./umass/cybersec/wheres_waldo.txt") as file:
    ans = ""
    for lines in file:
        dict = {}
        for i in lines:
            dict[i] = 0
        for j in lines:
            dict[j] += 1
        for k in dict:
            if dict[k] == 1:
                ans += k
        dict = {}

print(ans, end='')