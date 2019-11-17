# ViewModel_LiveData_Room_TaskManager
A task manager app utilizing ViewModel, LiveData and Room Android Jetpark Architectural Components

LifeCycleOwner
	These are components that have a life cycle of its own, for example, an Activity or a fragment

    When it comes to Android architecture we have Lifecycle as a class.
    The Lifecycle class has state and events enumeration, which it uses to determine
    the lifecycle of the LifeCycle Owner

LifeCycle Observer
    The LifeCycle Observer observes the activity and keeps track of its activity to perform some actions.
    The actions performed by the observer depends on the lifecycle of the lifecycle owner

LifeCycle Aware Components


ViewModel:
	this is responsible for providing data to the activity from the server. Viewmodel also makes sure that
	data remains constant in the activity even when the configuration changes for example, when the screen
	of the device is rotated and the activity is destroyed, when it creates another in the new orientation,
	the viewmodel provides the same data again to the activity so it maintains its initial state
	To implement a vielmodel class, simple create a class and extend the viewmodel call,
	then call a reference to it from your activity to provide required data

	    Benefits of Using ViewModel
	        1. View Model survives configuration changes, e.g Screen Rotation
	        2. Unlike onSaveInstanceState, ViewModel can handle Large amount of data eg. bitmap or user list from network
	        3. ViewModel Stores and Manages UI related data
	        4. ViewModel is destroyed only if the owner activity is completely destroyed, in onCleared()
	        5. It acts as the Communication layer between the Database and the UI
	        6. Using a ViewModel, the activity only has the responsibility of displaying the data,
	            while the VM handles the functionality of holding the data.


LiveData:
	When the viewmodel fetches the data from the server, we need a way of updating UI Activity with that data,
	because we cannot update the ui directly from a background thread. We achieve this using LiveData
	To implement this, make sure the data been returned from the webservice is of type LiveData on the viewmodel end.
	Then from the activity, attached the observer object to the livedata.
	As soon as the LiveData value is changed or updated, the change in the value of the
	LiveData object is observed in the activity, and the ui is updated
	In summary, LiveData makes it easy to update the ui, The activity observes the LiveData
	and UI is updated whenever there is any change in value in the livedata

    MutableLiveData extends the LiveData, and Provides public methods to update the stored data (postValue() and setValue())

    More Properties of Livedata:
        When the Activity is in the paused, stopped or destroyed state, it cannot observe the LiveData
        Hence, LiveData can be observed only when the Activity that is observing it, is in the Active or Resumed state

    Benefits of Using LiveData
        Keeps the UI updated in case of changes
        Automatically destroyed when associated LifeCycleOwner is destroyed
        No crashes due to stopped activites, since it is not observed when the Activity is stopped
        Can be shared by multiple resources