'''
CSC 421: Assignment 1

Current dependencies: Python 3.6.5
@author : Henri De Boever / Alex Deweert
'''

import sys, copy
from collections import OrderedDict
from collections import deque

class Tree():
	# Model a parent and child relationship between EnvStates
	def __init__(self):
		print("This tree will contain EnvState objects")

# Sucessor states: Modify one jug at a time; either fill, pour, or transfer the contents of one jug into another
# Maybe not the best solution since the branching factor is looking a bit more complicated than it has to be perhaps

# A state can be modified by an action, and must return another state
# The goal state is when we have 1 gallon left over in a jug
# An EnvState object takes 3 jugs whenever it is initialized
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
			print("Name: %s\t\tCapacity: %d gallons\t\tContents: %d gallons" % (jug.name, jug.max_capacity, jug.num_gallons))

	# Check to see if the current state is equivalent to the goal state (1 gallon remaining in any one of the 3 jugs)
	def goal_check(self):
		for index, jug in enumerate(self.water_jugs):
			if jug.num_gallons == 1:
				print("Solution found!")
				return True
		return False

def generate_all_possible_next_states(state):

	if(state.goal_check() is True):
		state.print_state()
		sys.exit(0)
	else:

		sucessor_states = []

		state.print_state()
		new_jug1 = WaterJug('12_gallon_jug', 12, 12)
		new_jug2 = WaterJug('8_gallon_jug', 8, state.water_jugs[1].num_gallons)
		new_jug3 = WaterJug('3_gallon_jug', 3, state.water_jugs[2].num_gallons)
		child_state1 = EnvState(new_jug1, new_jug2, new_jug3)
		sucessor_states.append(child_state1)
		child_state1.print_state()

		new_jug1 = WaterJug('12_gallon_jug', 12, state.water_jugs[0].num_gallons)
		new_jug2 = WaterJug('8_gallon_jug', 8, 8)
		new_jug3 = WaterJug('3_gallon_jug', 3, state.water_jugs[2].num_gallons)
		child_state2 = EnvState(new_jug1, new_jug2, new_jug3)
		sucessor_states.append(child_state2)
		child_state2.print_state()

		new_jug1 = WaterJug('12_gallon_jug', 12, state.water_jugs[0].num_gallons)
		new_jug2 = WaterJug('8_gallon_jug', 8, state.water_jugs[1].num_gallons)
		new_jug3 = WaterJug('3_gallon_jug', 3, 3)
		child_state3 = EnvState(new_jug1, new_jug2, new_jug3)
		sucessor_states.append(child_state3)
		child_state3.print_state()

		new_jug1 = WaterJug('12_gallon_jug', 12, 0)
		new_jug2 = WaterJug('8_gallon_jug', 8, state.water_jugs[1].num_gallons)
		new_jug3 = WaterJug('3_gallon_jug', 3,  state.water_jugs[2].num_gallons)
		child_state4 = EnvState(new_jug1, new_jug2, new_jug3)
		sucessor_states.append(child_state4)
		child_state4.print_state()

		new_jug1 = WaterJug('12_gallon_jug', 12, state.water_jugs[0].num_gallons)
		new_jug2 = WaterJug('8_gallon_jug', 8, 0)
		new_jug3 = WaterJug('3_gallon_jug', 3, state.water_jugs[2].num_gallons)
		child_state5 = EnvState(new_jug1, new_jug2, new_jug3)
		sucessor_states.append(child_state5)
		child_state5.print_state()

		new_jug1 = WaterJug('12_gallon_jug', 12, state.water_jugs[0].num_gallons)
		new_jug2 = WaterJug('8_gallon_jug', 8, state.water_jugs[1].num_gallons)
		new_jug3 = WaterJug('3_gallon_jug', 3, 0)
		child_state6 = EnvState(new_jug1, new_jug2, new_jug3)
		sucessor_states.append(child_state6)
		child_state6.print_state()

		new_jug1 = WaterJug('12_gallon_jug', 12, state.water_jugs[0].num_gallons + state.water_jugs[1].num_gallons)
		new_jug2 = WaterJug('8_gallon_jug', 8, 0)
		new_jug3 = WaterJug('3_gallon_jug', 3, state.water_jugs[2].num_gallons)
		child_state7 = EnvState(new_jug1, new_jug2, new_jug3)
		sucessor_states.append(child_state7)
		child_state7.print_state()

		new_jug1 = WaterJug('12_gallon_jug', 12, state.water_jugs[0].num_gallons + state.water_jugs[2].num_gallons)
		new_jug2 = WaterJug('8_gallon_jug', 8, state.water_jugs[1].num_gallons)
		new_jug3 = WaterJug('3_gallon_jug', 3, 0)
		child_state8 = EnvState(new_jug1, new_jug2, new_jug3)
		sucessor_states.append(child_state8)
		child_state8.print_state()

		new_jug1 = WaterJug('12_gallon_jug', 12, 0)
		new_jug2 = WaterJug('8_gallon_jug', 8, state.water_jugs[0].num_gallons + state.water_jugs[1].num_gallons)
		new_jug3 = WaterJug('3_gallon_jug', 3, state.water_jugs[2].num_gallons)
		child_state9 = EnvState(new_jug1, new_jug2, new_jug3)
		sucessor_states.append(child_state9)
		child_state9.print_state()

		new_jug1 = WaterJug('12_gallon_jug', 12, state.water_jugs[0].num_gallons)
		new_jug2 = WaterJug('8_gallon_jug', 8, state.water_jugs[1].num_gallons + state.water_jugs[2].num_gallons)
		new_jug3 = WaterJug('3_gallon_jug', 3, 0)
		child_state10 = EnvState(new_jug1, new_jug2, new_jug3)
		sucessor_states.append(child_state10)
		child_state10.print_state()

		new_jug1 = WaterJug('12_gallon_jug', 12, 0)
		new_jug2 = WaterJug('8_gallon_jug', 8, state.water_jugs[1].num_gallons)
		new_jug3 = WaterJug('3_gallon_jug', 3, state.water_jugs[0].num_gallons + state.water_jugs[2].num_gallons)
		child_state11 = EnvState(new_jug1, new_jug2, new_jug3)
		sucessor_states.append(child_state11)
		child_state11.print_state()

		new_jug1 = WaterJug('12_gallon_jug', 12, state.water_jugs[0].num_gallons)
		new_jug2 = WaterJug('8_gallon_jug', 8, 0)
		new_jug3 = WaterJug('3_gallon_jug', 3, state.water_jugs[1].num_gallons + state.water_jugs[2].num_gallons)
		child_state12 = EnvState(new_jug1, new_jug2, new_jug3)
		sucessor_states.append(child_state12)
		child_state12.print_state()

		return sucessor_states

# Gts rid of duplicate states and resolve cases with overflow
def get_legal_states(state_list):

	set_of_states = OrderedDict()

	for index, state in enumerate(state_list):
		for jug in state:
			if(jug.num_gallons > jug.capacity):
				jug.num_gallons = jug.max_capacity
		set_of_states[index] = state

	return set_of_states

def state_tree_search(legal_states):

	frontier = deque()

	while True:
		if len(legal_states) == 0:
			return None
		else:
			for key, value  in legal_states.items():
				frontier.add(value)
			state  = frontier.popleft()






# A water jug object is held within a state attribute list called water_jugs
class WaterJug():

	# Class initializer

	def __init__(self, name, max_capacity, num_gallons):
		print("\nInstantiating a new WaterJug() object.\n")
		self.name = name
		self.max_capacity = max_capacity
		self.num_gallons = num_gallons

	# Class Functions

	def get_num_gallons(self):
		print('\n%s has %s gallons in it.' % (self.name, self.num_gallons))

	# Empties the contents of the current jug wihout modifying any of the others.
	def pour_on_ground(self):
		if self.num_gallons > 0:
			self.num_gallons = 0
		elif self.num_gallons == 0:
			print("Cannot pour water from an empty jug")

	def fill_from_tap(self):
		print("\nFilling %s from the tap.\n" % self.name)
		# Filling jug from the faucet
		if self.num_gallons < self.max_capacity:
			self.num_gallons = self.max_capacity
		else:
			print("%s is already full" % self.name)

	def transfer_all_water(self, other_jug):
		# Check if other jug is a jug object before proceeding
		if isinstance(other_jug, WaterJug):
			print("\nTransfering %s gallons from %s to %s\n" % (str(self.num_gallons), self.name, other_jug.name))
			# Transfer the water
			other_jug.num_gallons += self.num_gallons
			self.num_gallons -= self.num_gallons
			# Catch the case of water overflow when pouring an amount larger than the max_capacity of other_jug into the other jug
			if other_jug.num_gallons > other_jug.max_capacity:
				other_jug.num_gallons = other_jug.max_capacity
			print("%s now contains %d gallons\n" % (other_jug.name, other_jug.num_gallons))
		else:
			print("Incorrect params")
			sys.exit(0)

	def fill_other_jug_from_self(self, other_jug):
		# Check if other jug is a jug object before proceeding
		if isinstance(other_jug, WaterJug):
			print("\nTransfering water from %s to fill %s" % (str(self.num_gallons), self.name, other_jug.name))
			if self.num_gallons > (other_jug.max_capacity - other_jug.num_gallons):
				self.num_gallons -= (other_jug.max_capacity - other_jug.num_gallons)
				other_jug.num_gallons += (other_jug.max_capacity - other_jug.num_gallons)
			else:
				print("Current jug does not have enought water to fill other jug.")
		else:
			print("Incorrect params")
			sys.exit(0)

# The main function serves as a test harness for class instances
def main(argv):

	jug1 = WaterJug('12_gallon_jug', 12, 0)
	jug2 = WaterJug('8_gallon_jug', 8, 0)
	jug3 = WaterJug('3_gallon_jug', 3, 0)

	initial_state = EnvState(jug1, jug2, jug3)
	state_list = generate_all_possible_next_states(initial_state)
	legal_states = resolve_overflows(state_list)


	# jug1.fill_from_tap()
	#
	# next_state = EnvState(jug1, jug2, jug3)
	# next_state.print_state()
	#
	# jug1.transfer_all_water(jug2)
	# third_state = EnvState(jug1, jug2, jug3)
	# third_state.print_state()

	# test_jug1.get_num_gallons()
	# test_jug2.get_num_gallons()
	# test_jug3.get_num_gallons()
	#
	# test_jug1.transfer_all_water(test_jug2)


if __name__ == "__main__":
	main(sys.argv)
