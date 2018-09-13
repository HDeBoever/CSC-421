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

	# Class initializer
	def __init__(self, jug1, jug2, jug3):
		print("Instantiating a new state")

		# Track the water jug's contents in a state instance, self.water_jugs is a list that contains 3 jugs at each state
		self.water_jugs = []
		self.water_jugs.append(jug1)
		self.water_jugs.append(jug2)
		self.water_jugs.append(jug3)

		print(self.water_jugs)

	# Class Functions

	def print_state(self):
		for index, jug in enumerate(self.water_jugs):
			print(jug.name, jug.num_gallons)

	# Check to see if the current state is equivalent to the goal state
	def goal_check(self):
		for index, jug in enumerate(self.water_jugs):
			if jug.num_gallons == 1:
				return True
		return False

# A water jug object is held within a state
class WaterJug():

	# Class initializer
	# The attributes below are specific to each instance of a WaterJug object
	def __init__(self, name, max_capacity, num_gallons):
		print("\nInstantiating a new WaterJug() object.\n")
		self.name = name
		self.max_capacity = max_capacity
		self.num_gallons = num_gallons

	# Class functions are defined below
	def get_num_gallons(self):
		print('\n%s has %s gallons in it.' % (self.name, self.num_gallons))

	def pour_jug_on_groud(self):
		if self.num_gallons > 0:
			self.num_gallons = 0
		elif self.num_gallons == 0:
			print("Cannot pour water from an empty jug")

	def fill_from_tap(self):
		# Filling jug from the faucet
		if self.num_gallons < self.max_capacity:
			self.num_gallons = self.max_capacity
		else:
			print("%s is already full" % self.name)

	def transfer_water_to_other_jug(self, other_jug):
		# Check if other jug is a jug object before proceeding
		if isinstance(other_jug, WaterJug):
			print("\nTransfering %s gallons from %s to %s" % (str(self.num_gallons), self.name, other_jug.name))
			# Transfer
			other_jug.num_gallons += self.num_gallons
			# Catch the case of water overflow when pouring an amount larger than the max_capacity of other_jug into the other jug
			if other_jug.num_gallons > other_jug.max_capacity:
				other_jug.num_gallons = other_jug.max_capacity
			print("%s now contains %d gallons" % (other_jug.name, other_jug.num_gallons))
		else:
			print("Incorrect params")
			sys.exit(0)

# The main function serves as a test harness for the water jug class instances
def main(argv):

	jug1 = WaterJug('12_gallon_jug', 12, 0)
	jug2 = WaterJug('8_gallon_jug', 8, 0)
	jug3 = WaterJug('3_gallon_jug', 3, 0)

	initial_state = EnvState(jug1, jug2, jug3)
	initial_state.print_state()

	# test_jug1.get_num_gallons()
	# test_jug2.get_num_gallons()
	# test_jug3.get_num_gallons()
	#
	# test_jug1.transfer_water_to_other_jug(test_jug2)


if __name__ == "__main__":
	main(sys.argv)
