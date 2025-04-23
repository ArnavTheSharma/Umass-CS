import os

FLAG_OFFSET = int(input("FLAG_OFFSET: "))
cipher_hex = "60797d6e6c455d490d41774a1b4e1d705e1c155314557d415452075211573957033b0459015e5b16"

BURGER_LAYERS = ["Bun (1)", "Lettuce (2)", "Tomato (3)", "Cheese (4)", "Patty (5)", "Onion (6)", "Pickle (7)", "Mayonnaise (8)", "Egg (9)", "Avocado (10)"]

def print_towers(towers):
    for i, tower in enumerate(towers):
        print(f"Tower {i + 1}: {[BURGER_LAYERS[layer - 1] for layer in tower]}")
    print()

def move_disk(towers, from_tower, to_tower):
    if not towers[from_tower]:
        print("Invalid move: Source plate is empty.")
        return False
    if towers[to_tower] and towers[to_tower][-1] < towers[from_tower][-1]:
        print("Invalid move: Cannot place larger burger layer on smaller burger layer.")
        return False
    towers[to_tower].append(towers[from_tower].pop())
    return True

def towers_of_hanoi():
    num_disks = int(input("Enter the number of burger layers (1-10): "))
    while num_disks < 1 or num_disks > 10:
        print("Please enter a number between 1 and 10 inclusive.")
        num_disks = int(input("Enter the number of burger layers (1-10): "))
    towers = [list(range(num_disks, 0, -1)), [], []]

    def solver(n, start, destination): 
        if (start > 2 or destination > 2 or start < 0 or destination < 0):
            print("Please enter a number between 0 and 2, inclusive")
            return
        if n == 1:
            move_disk(towers, start, destination)
            print_towers(towers)
            return
        aux = 3 - start - destination
        solver(n - 1, start, aux)
        solver(1, start, destination)
        solver(n - 1, aux, destination)

    print_towers(towers)
    print(f"Congratulations! You solved the puzzle. The last { num_disks} bits of the offset used to encrypt the flag are {bin(FLAG_OFFSET & ((1 << ( num_disks )) - 1))}.")
    if num_disks >= 9:
        print("Waoh, that is a big sandwich! The offset only has 6 bits though...")

def key_gen_func(i: int) -> int:
    return i

def encrypt(message: bytes, offset = FLAG_OFFSET) -> str:
    encrypted = bytearray()
    if offset is None:
        offset = os.urandom(1)[0]
    for (i, char) in enumerate(message):
        encrypted.append(char ^ (key_gen_func(i + offset) & 0xFF))
    return encrypted.hex()

def decrypt(cipher_hex, offset):
    cipher = bytearray.fromhex(cipher_hex)
    decrypted = bytearray()
    for i, b in enumerate(cipher):
        decrypted.append(b ^ (key_gen_func(i + offset) & 0xFF))
    return decrypted.decode(errors='ignore')



def try_key_funcs():
    cipher = bytearray.fromhex(cipher_hex)

    print("\nTrying alternative key_gen_func assumptions:")
    key_variants = [
        lambda i: i,
        lambda i: i * 2,
        lambda i: i * i,
        lambda i: (i * 17 + 31) % 256,
        lambda i: (i ^ 0xAB) & 0xFF,
        lambda i: 0x42,  # constant key
        lambda i: (i % 7) * 42,
        lambda i: (i * 3 + 7) & 0xFF,
        lambda i: ((i >> 1) ^ i) & 0xFF,
        lambda i: (i ^ (i >> 3)) & 0xFF,
        lambda i: (~i) & 0xFF,
    ]

    for variant_id, func in enumerate(key_variants):
        for offset in range(256):
            try:
                plain = ''.join(chr(b ^ (func(i + offset) & 0xFF)) for i, b in enumerate(cipher))
                if "UMASS" in plain:
                    print(f"Variant {variant_id}, Offset {offset}: {plain}")
            except:
                continue


if __name__ == "__main__":
    choice = input("Options\n1. Make a Burger\n2. Encrypt with random offset\n3. Brute-force decrypt flag\nSelect an option: ")
    if choice == '1':
        towers_of_hanoi()
    elif choice == '2':
        s = input("Enter a string to encrypt: ")
        print(encrypt(s.encode(), offset=None))
    elif choice == '3':
        try_key_funcs()
        # for offset in range(256):
        #     plain = decrypt(cipher_hex, offset)
        #     if "UMASS" in plain:
        #         print(f"Offset {offset}: {plain}")
