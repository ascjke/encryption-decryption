# encryption-decryption

In this pet project we have a CLI program where we can encrypt messages, and decrypt them.
Encryption and decryption algorithms are used Ceasar cipher or Unicode cipher.

https://user-images.githubusercontent.com/101511051/164888901-464d49d8-7282-4676-a145-7bf7c8701c67.mp4

User can set options of program with with command-line arguments instead of the standard input. There are following arguments:

-mode (this argument determine the program’s mode (enc for encryption, dec for decryption))

-key (the second argument is an integer key to modify the message)

-data (third argument is a text or ciphertext to encrypt or decrypt)

-in (specify the full name of a file to read data)





-out (specify the full name of a file to write the result)

-alg (specify the algorithm of encrypt/decrypt)


In this project applied factory method to parse user input and create an required instances of Ciphers

    If there is no -mode, the program should work in enc mode.
    If there is no -key, the program should consider that key = 0.
    If there is no -data, and there is no -in the program should assume that the data is an empty string.
    If there is no -out argument, the program must print data to the standard output.
    If there are both -data and -in arguments, your program should prefer -data over -in.
    If there is no -alg you should default it to shift.


    


