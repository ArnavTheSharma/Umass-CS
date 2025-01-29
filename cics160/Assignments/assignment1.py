def is_prime(n):
    if n <= 1:
        return False
    else:
        for i in range(2, int(n/2)+1):
            if n%i == 0:
                return False
        return True


def are_relatively_prime(a, b):
    if a%b == 0 or b%a == 0:
        return False
    elif a>b:
        for i in range(2, int(b/2)+1):
            if a%i == 0 and b%i == 0:
                return False
        return True
    else:
        for i in range(2, int(a/2)+1):
            if a%i == 0 and b%i == 0:
                return False
        return True


def primes(n):
    list = []
    for i in range(1, n+1):
        if is_prime(i) == True:
            list.append(i)
    return list
        
def prime_decomposition(n):
    list = []
    i = 2
    while n != 1:
        if n%i == 0 and is_prime(i) == True:
            list.append(i)
            n /= i
            i = 2
        else:
            i+=1
    return list
        
    

def has_prime_decomposition_of_size_2_and_factors_are_different(n):
    list = prime_decomposition(n)
    if len(list) == 2 and list[1] != list[0]:
        return True
    else:
        return False
