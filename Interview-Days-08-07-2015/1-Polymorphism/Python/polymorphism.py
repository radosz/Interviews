class Singer():

    def __init__(self, name):
        self.__name = name

    # Abstract method, defined by convention only
    def songs(self):
        raise NotImplementedError("Subclass must implement abstract method")


class Keranov(Singer):

    def __init__(self):

        with open("Keranov.txt", "r") as songs:
            self.song_lst = songs.read().replace("\n", "").split(",")
            songs.close()
        super().__init__("Keranov")

    def songs(self):
        return self.song_lst


class QvkataDLG(Singer):

    def __init__(self):
        with open("qvkata.txt", "r") as songs:
            self.song_lst = songs.read().replace("\n", "").split(",")
            songs.close()
        super().__init__("QvkataDLG")

    def songs(self):
        return self.song_lst


def main():
    qvkata = QvkataDLG()
    keran = Keranov()
    print(qvkata.songs())
    print(keran.songs())
    print("qvakata.songs() != keran.songs() ->", qvkata.songs() != keran.songs())

if __name__ == '__main__':
    main()
