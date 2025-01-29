# unitest is a built in py module, helps us test units of our code induvidually and seperately

import unittest
from primes import is_prime

class TestFirstAssignment(unittest.TestCase): #class inherits from TestCase
    # this function will check if it returns correctly for an input of 2
    def test_isPrimeGivesCorrectAnswerForNEqualTwo(self): #notice how the name starts with test_
        self.assertEqual(True, is_prime(2))

    # however, we dont want to keep inputting different numbers, like 23, 44, 59, etc. so we use unitest

    # VSCODE Leftmost menu open chemistry beacon icon and click "Configure Python Tests", then click on the unittest framework (the one we imported)
    #   then click the test_*.py (because thats how we named our files, you can do the other options as well)
    #   then click the run button (not on the folder, it'll run one of the functions, but the subfile with the induvidual function you want to test)

    # you can also run this in your cmd prompt: 
    # python3 -m unittest test_assignment1.py