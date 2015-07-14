class HashMap:

    def __init__(self):
        self.keys = []
        self.values = []

    def __get_ind_key(self, key):
        return self.keys.index(key)

    def get(self, key):
        if self.has(key):
            ind = self.__get_ind_key(key)
            return self.values[ind]
        raise KeyNotFound("Key not found in this HashMap")

    def has(self, key):
        return key in self.keys

    def remove(self, key):
        if self.has(key):
            item = self.get(key)
            self.keys.remove(key)
            self.values.remove(item)

    def add(self, key, value):
        if not self.has(key):
            self.keys.append(key)
            self.values.append(value)
        else:
            self.remove(key)
            self.add(key, value)

    def values(self):
        return self.values

    def keys(self):
        return self.keys

    def items(self):
        return zip(self.keys, self.values)


class KeyNotFound(Exception):
    pass


def main():
    hm = HashMap()
    hm.add("radorado", 1)
    hm.add("ivo", 2)
    hm.add("rado", 3)
    for k in hm.keys:
        print(k)
    for v in hm.values:
        print(v)
    for k, v in hm.items():
        print(k)
        print(v)

if __name__ == '__main__':
    main()
