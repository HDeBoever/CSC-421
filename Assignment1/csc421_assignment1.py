'''
CSC 421: Assignment 1

Current dependencies: Python 3.6.5
@author : Henri De Boever / Alex Deweert
'''

import sys
from collections import OrderedDict

# A state can be modified by an action, and must return another state
# The goal state is when we have 1 liter left over in a jug
class EnvState():

	def __init__(self):
		print("Instantiating a new state")

		# Track the water jug's contents in a state instance
		water_jugs = []

# A water jug object is held within a state
class WaterJug():

	# Class initializer
	# The attributes below are specific to each instance of a WaterJug object
	def __init__(self):

		print("\nInstantiating a new WaterJug() object.\n")


		# put relevant instance attributes here

	# Class functions are defined below



# The main function serves as a test harness for the water jug class instances
def main(argv):

	print("Creating a new EnvState object")
	print("Creating a new WaterJug object")


if __name__ == "__main__":
	main(sys.argv)
