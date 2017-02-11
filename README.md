# RoomBooking
Room booking app prototype

##Tools used:
Retrofit2 for network calls, Butterknife for binding views, glide for images, android support library (cardviews and recycler views) and design support library for Fabs
also [Multislider](https://android-arsenal.com/details/1/3976) control for selecting time ranges for rooms
## App Structure:
MVP design pattern, devided the app into packages related to each screen "RoomsListing" package, "RoomDetail" and "BookRoom" package
##Development Steps:
1- I started developing the room listing screen, simply by calling the api, and using a recyclerview to display each room info.<br /><br />
2- made a custom control (controls/RoomAvailabilityDisplayBar.java) it extends FrameLayout,
you specify the startHour, endHour and subIntervals(15 minutes) and the control draws time slots, for each hour divided by the interval,
and then checks for each room what are the available timings and give them a different color than red.<br /><br />
3- Second screen was room details screen, it contains the room info, also the availability bar for the room, and clickable gallery images for each room.<br /><br />
4- Booking Room screen, to achieve the range slider effect over the availability bar, just added a two thumb seekbar (MultiSlider) over the availability bar
and gave it (hours*subintervals) steps in order to be accurate (each slide step is 15 minutes)
##TODO: 
What isn't yet implemented, is adding the attendees list , which would be implemented by using a simple recycler view that holds the already added attendees, and a simple form to add new ones.
validations would by by simple helper static classes that check for name, email, and phone regexes.
and the post this list as the documentation api required. and change the multi slider style to be more convinient.


