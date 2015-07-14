def permutations(word):
    count = 0
    sorted_word = "".join(sorted(word))

    for n in range(int(numb)):
        word = input()
        if len(word) > 1 and len(word) <= 100:
            if sorted_word == "".join(sorted(word)):
                count += 1
    return count

word = input()
numb = input()
print(permutations(word))
