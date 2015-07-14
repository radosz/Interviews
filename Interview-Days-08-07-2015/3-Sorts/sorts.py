from random import shuffle

# O(n * lgn)

def merge(items):
    """ Implementation of mergesort """
    if len(items) > 1:

        mid = len(items) // 2        # Determine the midpoint and split
        left = items[:mid]
        right = items[mid:]

        merge(left)            # Sort left list in-place
        merge(right)           # Sort right list in-place

        l, r = 0, 0
        for i in range(len(items)):     # Merging the left and right list

            lval = left[l] if l < len(left) else None
            rval = right[r] if r < len(right) else None

            if (lval and rval and lval < rval) or rval is None:
                items[i] = lval
                l += 1
            elif (lval and rval and lval >= rval) or lval is None:
                items[i] = rval
                r += 1
            else:
                raise Eitemsception(
                    'Could not merge, sub arrays sizes do not match the main array')
    return items

# O(n^2)

def bubble_sort(items):
    """ Implementation of bubble sort """
    for i in range(len(items)):
        for j in range(len(items) - 1 - i):
            if items[j] > items[j + 1]:
                items[j], items[j + 1] = items[j + 1], items[j]
    return items

# One sort, that is slower than O(n^2)

def inorder(items):
    i = 0
    j = len(items)
    while i + 1 < j:
        if items[i] > items[i + 1]:
            return False
        i += 1
    return True


def bogo(items):
    while not inorder(items):
        shuffle(items)
    return items
