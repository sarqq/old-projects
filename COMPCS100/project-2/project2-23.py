"""
COMPCS100 - project 2
author: sarqq

Prints out central tendency statistics of user given values.
Statistics given: median and mean temperatures, temperatures deviating from the median
"""

def read_values():
    """
    Read temperature values from user

    :return values: list, user input
    """
    temps = []
    days = int(input("Enter amount of days: "))
    
    try:
        for i in range(days):
            temp = float(input(f"Enter day {i+1}. temperature in Celcius: "))
            temps.append(temp)
            
    except ValueError:
        print("Error: temperature value not a number.")
        return None

    return temps


def calculate_median(ord_list):
    """
    Find median value of a list.
    Median is the middle value of a sorted list if odd, and the mean of two
    middle values of a sorted list if even.

    :param  sorted_list:    list, a sorted list of temperature values
    :return median:         float, median value of list
    """
    median = 0.0
    midpoint = 0

    #if list not empty, get first middle value
    if ord_list:
        midpoint = len(ord_list) // 2
    else:
        print("Error: empty list")
        return None
    
    #if list length even -> median is the average of two middle values
    if len(ord_list)%2 == 0:
        midpoint2 = midpoint-1
        median = (ord_list[midpoint] + ord_list[midpoint2]) / 2
    #if list length odd -> median is the middle value
    else:
        median = ord_list[midpoint]

    return median


def calculate_deviations(temps, mean, median):
    """
    Separate temperature values deviating from the median to their own lists.

    :param  temps:          list, temperature values (float)
            mean:           int, mean of list values
            median:         float, median value of given temperatures
    :return under_median:   list, temperature values under median
            over_median:    list, temperature values over or equal to median
    """
    under_median = []
    over_median = []
    
    #iterate over list and find values < median and values >= median
    for i in range(len(temps)):
        if temps[i] >= median:
            over_median.append((i+1, temps[i]))
        else:
            under_median.append((i+1, temps[i]))

    return under_median, over_median

def main():
    #variables
    temps = read_values()
    mean = sum(temps)/len(temps)
    median = calculate_median(sorted(temps))
    under_median, over_median = calculate_deviations(temps, mean, median)

    if not under_median or not over_median:
        print("Error!")
        return

    #print results
    print(f"\nTemperature mean: {mean:.1f}C")
    print(f"Temperature median: {median:.1f}C")
        
    print(f"\nOver or at median were: ")
    for i in over_median:
        print(f"Day {i[0]:2}. {i[1]:5.1f}C, difference to mean: {i[1]-mean:5.1f}C")
        
    print(f"\nUnder median were: ")
    for i in under_median:
        print(f"Day {i[0]:2}. {i[1]:5.1f}C, difference to mean: {i[1]-mean:5.1f}C")
        

if __name__=="__main__":
    main()
