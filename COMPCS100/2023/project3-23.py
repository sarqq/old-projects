"""
COMPCS100 - project 3
author: sarqq

A simple plaintext social network with a simple text-based GUI.
User can print out network data and add new connections.

"""

#constants
#map for commands and the number of their expected numbre of parameters
COMMANDS = {"p":0, "a":2, "f":1, "c":2, "q":0}
DEFAULT_FILENAME = "social.txt"

#error messages
ERROR_COMMAND = "Invalid command:"
ERROR_PARAMS1 = "Invalid number of parameters, expected:"
ERROR_PARAMS2 = "Invalid parameter:"
ERROR_NETWORK = "Error: social network not found."

def read_file(filename):
    """
    Read

    :param  filename:   string, input file name
    :return network:    dict, the network with people as keys (string)
                        and their friends (list) as values
    """

    network = {}
    
    try:
        #open and read file
        file = open(filename, mode="r")

        for line in file:
            line = line.rstrip()

            #ignore lines of comments
            if len(line)>0 and line[0] =="#": continue

            params = line.split(";")

            #check parameters
            if len(params) < 2:
                print(f"Error: line '{line}' has incorrect format.")
                return None

            for name in params:
                if not is_valid(name): return None

            #add a new connection to the social network
            friend1 = params[0]
            for friend2 in params[1:]:
                add_friendship(network, friend1, friend2)

        file.close()
        return network

    except OSError:
        print(f"Error: file '{filename}' not found!")
        return None

def add_friendship(network, name1, name2):
    """
    Add new connection to network.
    New node: add name1 as new key to dict with empty list as value.
    Existing node: add name2 to list of name1.
    Repeat this for name2.

    :param  network:    dict, the social network
            name1:      string, name of first connecting node
            name2:      string, name of second connecting node
    """
    if not is_valid(name1) or not is_valid(name2): return

    if name1 == name2:
        print("Must be two different people.")
        return

    #add new nodes to network
    for name in [name1, name2]:
        if name not in network:
            network[name] = []

    #add new connections to nodes
    if name1 not in network[name2]:
        network[name2].append(name1)
        print(f"Added new connection: {name2} - {name1}")
    
    if name2 not in network[name1]:
        network[name1].append(name2)
        print(f"Added new connection: {name1} - {name2}")


def print_friends(network, name):
    """
    Print connections from/to specified node in alphabetical order.

    :param  network:    dict, the social network
            name:       string, name of inspected node
    """
    #check parameters
    if not network:
        print(ERROR_NETWORK)
        return

    if not is_valid(name): return

    #print person and their associated friends in alphabetical order
    print(f"{name}'s friends: ")
    for friend in sorted(network[name]): print(f"- {friend}")

def print_common_friends(network, name1, name2):
    """
    Print connections shared by specified nodes in alphabetical order.

    :param  network:    dict, the social network
            name1:      string, name of first inspected node
            name2:      string, name of second inspected node
    """
    #check parameters
    if not network:
        print(ERROR_NETWORK)
        return

    if not is_valid(name1) and not is_valid(name2): return

    if name1 == name2:
        print("Must be two different people.")
        return

    #find common elements with set intersection
    common_friends = set(network[name1]).intersection(set(network[name2]))

    if not common_friends:
        print(f"{name1} and {name2} have no common friends.")
        return

    #print common elements
    print(f"Common friends of {name1} and {name2}:")
    for friend in sorted(common_friends):
        print(f"- {friend}")

def print_network(network):
    """
    Print all network nodes and their connections in alphabetical order.

    :param  network:    dict, the social network
    """
    #check parameters
    if not network:
        print(ERROR_NETWORK)
        return

    #print people and their associated friends in alphabetical order
    for name in sorted(network.keys()):
        print(name)
        for friend in sorted(network[name]):
            print(f"- {friend}")

def is_valid(param):
    """
    Checks if given parameter is non-empty and alphanumerical.

    :param  param:      string, the specified parameter
    :return True/False:
    """
    if not param or not param.isalpha():
        print(f"{ERROR_PARAMS2} '{param}'")
        return False

    return True

def ui(network):
    """
    Basic text-based UI to prompt user into giving input and forwarding said input
    into other functions.

    :param  network:    dict, the social network
    """
    #check parameters
    if not network:
        print(ERROR_NETWORK)
        return

    while True:
        #prompt user
        print("\nEnter one of the commands:\n"\
                "[Pp]rint/[Aa]dd/[Ff]riends/[Cc]ommon/[Qq]uit\n"\
                "followed by one or more names if needed.\n")
        
        #read and check input
        entry = input("Enter command: \n>> ").strip().split()

        if not entry:
            print(f"{ERROR_COMMAND}!")
            continue

        command = entry[0].lower()
        params = entry[1:]

        #check parameters
        if command not in COMMANDS:
            print(f"{ERROR_COMMAND} '{command}'")
            continue

        if len(params) != COMMANDS[command]:
            print(f"{ERROR_PARAMS1} {COMMANDS[command]}")
            continue

        #execute command
        #print network, expected format: p
        if command == "p": print_network(network)
        #add node connections, expected format: a <name1> <name2>
        elif command == "a": add_friendship(network, params[0], params[1])
        #print node connections, expected format: f <name> 
        elif command == "f": print_friends(network, params[0])
        #print common connections between nodes, expected format: c <name1> <name2>
        elif command  == "c": print_common_friends(network, params[0], params[1])
        #quit, expected format: q
        elif command  == "q":
            print("Farewell cruel world...")
            return

def main():
    filename = input("Enter the name of the input file: ")

    #use default file name if none given by user
    if not filename: filename = DEFAULT_FILENAME

    network = read_file(filename)

    #network created -> run UI
    if network:
        print(f"File {filename} successfully read.")
        ui(network)

if __name__ == "__main__":
    main()
