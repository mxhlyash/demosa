Binary Search (el classico)

   def search(self, nums: List[int], target: int) -> int:
        lo, hi = 0, len(nums) - 1
        while lo < hi:
            mid = (lo + hi) // 2
            if nums[mid] >= target:
                hi = mid
            else:
                lo = mid + 1

        if nums[lo] == target:
            return lo
        else:
            return -1


KEY INTUITION: condition is NOT nums[mid] == target. It’s >= because with advanced template we need to minimize/maximize; it must apply to all above/below


Search Insert Position

# Template
# minimize index such that nums[index] >= target
# bounds: 0, len(nums)
#   * len(nums) bc we may need to place after the last element in the array
# condition: nums[index] == target
def searchInsert(self, nums: List[int], target: int) -> int:
   lo, hi = 0, len(nums)
   while lo < hi:
       mid = (lo + hi) // 2
       if nums[mid] >= target:
           hi = mid
       else:
           lo = mid + 1

   return lo






First Bad Version
# applying advanced template:
# reformulate problem: find the minimal k that satisfies condition
# include all versions: lo=1, hi=n
# return lo because it is minimal k satisfying condition
# condition is already defined
def firstBadVersion3(self, n: int) -> int:  # beats 95%/95%!
   lo, hi = 1, n
   while lo < hi:
       mid = (lo+hi) // 2
       if isBadVersion(mid):
           hi = mid
       else:
           lo = mid + 1

   return lo


Square Root
# find maximal n such that n^2 <= x
# bounds: lo=0, hi=x
# condition: n^2 <= x
# maximize: lo-1 and not conditon
def mySqrt(self, x: int):
   if x in (0, 1):
       return x
   lo, hi = 0, x
   while lo < hi:
       mid = (lo + hi) // 2
       if not mid * mid <= x:
           hi = mid
       else:
           lo = mid + 1
   return lo - 1






Capacity to Ship Packages Within D Days
# advanced binary search template
# we want the minimal weight that satisfies the days requirement (ship all packages within D days)
# monotonicity: if we can successfully ship all packages within D days with capacity m, then we can also ship them for any m2 > m
# condition: can_ship(x) --> returns whether it's possible to ship all packages within D days
# bounds: capacity ≥ max(weights) or else that package would get stuck.
#         capacity ≤ sum(weights), as any value over that we just ship all in 1 day
def shipWithinDays(self, weights: List[int], days: int) -> int:
   def can_ship(capacity):
       d, total = 1, 0
       for w in weights:
           total += w
           # if it's too heavy so we need to wait for the next day
           if total > capacity:
               d += 1  # go to next day
               total = w
               # the amount of days required is more than we are allowed
               if d > days:
                   return False
       return True

   lo, hi = max(weights), sum(weights)
   while lo < hi:
       mid = (lo + hi) // 2
       if can_ship(mid):
           hi = mid
       else:
           lo = mid + 1

   return lo


Minimum Time to Complete Trips
# minimum total time such that trips >= totaltrips
#   * if it's valid for totaltime+n, then it's valid for totaltime+n-1 etc
# bounds:
#   * minimum=1
#   * maximum=totalTrips*min(time) bc worst case scenario, it will take totalTrips trips of the min time bus to reach total trips
def minimumTime(self, time: List[int], totalTrips: int) -> int:
   def valid_time(t):
       ans = 0
       for i in time:
           ans += t // i
           if ans >= totalTrips:
               return True
       return False

   lo, hi = 1, min(time) * totalTrips
   while lo < hi:
       mid = (lo + hi) // 2
       if valid_time(mid):
           hi = mid
       else:
           lo = mid + 1

   return lo


Koko Eating Bananas
# minimize k such that within h hours every pile is eaten
# for any k that works, k+1 etc works, for any k that doesn't work, k-1 etc doesn't work: binary search
# bounds
#   * min: 1 - if u can only eat at 1/h that's ideal
#   * max: max(piles) - u never need to eat more per h than the max pile
def minEatingSpeed(self, piles: List[int], h: int) -> int:
   def can_eat(b):
       hours = 0
       for p in piles:
           hours += math.ceil(p/b)
           if hours > h:
               return False

       return hours <= h

   lo, hi = 1, max(piles)
   while lo < hi:
       mid = (lo + hi) // 2
       if can_eat(mid):
           hi = mid
       else:
           lo = mid + 1

   return lo