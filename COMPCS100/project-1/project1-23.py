"""
COMPCS100 project 1 - running statistics
author: sarqq

Checks average user running distance from input.
Only periods with <3 consecutive 0 days are considered valid.
"""

MAX_FAILURES = 3
GOAL_AVERAGE = 3.0

def main():
    #variables
    days = 0
    failures = 0
    distances = []
    
    try:
        days = int(input("Enter the number of days: "))

        for i in range(days):
            distance = float(input(f"Enter day {i+1} running length: "))
        
            if distance > 0:
                #increase total distance covered, reset failures
                distances.append(distance)
                failures = 0
            else:
                failures += 1
                
                #3 consecutive 0 days -> quit program
                if failures == MAX_FAILURES:
                    print("\nYou had too many consecutive lazy days!")
                    return
    except ValueError:
        print("Error: input invalid.")

    #calculate average distance covered
    avg_distance = sum(distances) / days

    #print results
    if avg_distance < GOAL_AVERAGE:
        print(f"\nYour running average of {avg_distance} km was too low!")
    else:
        print(f"\nYou were a persistent runner! With an average of {avg_distance:.2f} km.")
        

if __name__=="__main__":
    main()
