# nyc-popular-articles

## Tools and Frameworks Used
1.) **Dagger 2.11** for dependency injection.
  
2.) **Rx-Java 2** for async and reactive programming between the view model, respository and data sources.Because Rx-java is very handy when inter-thread communication is required. Its also very **versatile** in performing a lot of **transformations** and **filters** on data. 
  
3.) **Live Data** for smooth communication between the viewmodel and the views as live data is **lifecycle aware**.  
  
4.) **Android Architecture Components ViewModel** inorder to implement MVVM architecture.

## Brief Architectural walkthrough
As already mentioned we are using the **MVVM** pattern.In addition to that **Repository Pattern** has been used to make the code much more **configurable** and **testable**. Please see the following diagram carefully.   

![](https://drive.google.com/uc?export=download&id=1PwZbXszQexClFSDDeJ3fEG7CmpBgvDgr)

## Testing
The **unique** and integral part of this project is testing.Code has been made **highly testable** and **configurable** using the best practices both old and new ones.  
The project has three types of tests:-  
1.) **Unit Tests** :- Test the core business logic, i.e the **viewmodel**. Are very fast as they run directly on JVM.  
  
2.) **Instrumentation Tests** :- Test how the views(fragments and activities) are displaying the data. Have been written using **Espresso**.  
  
3.) **Integrations Tests** :- These would test **interaction** of deifferent views.Have been written using **Espresso**

## How to run Tests  
1.) **Unit Tests** 
  
![](https://drive.google.com/uc?export=download&id=1Naw-cDPQs9ONmrEHjlmg4kmtZCfM401E)  
  
2.)**Instrumentation/Integration Tests**  
  
![](https://drive.google.com/uc?export=download&id=1cinZft7q0o-OCctqhxnkdKBW0L1y2Jun)  

## Security  
For security purspose NYC Times API key has been stored via **NDK**.  

## Future Scope 
  
1.) **Offline Support using Room** 
Since we are using the **Repository Pattern** and **Rx-Java**, it will be very easy for us to leevrage Room and **let the Room database be the single source of truth** which would **sync** data from the **cloud**.  
  
2.) **Testing DB queries and Migrations**. 
We can directly test our DB queries and migrations using Room. I have done it before and have also **written a blog post series** on the same titled **Testing the Un-Testable Using Android Acrchitecture Components** .  

https://proandroiddev.com/testing-the-un-testable-and-beyond-with-android-architecture-components-part-1-testing-room-4d97dec0f451  

  
https://proandroiddev.com/testing-the-un-testable-and-beyond-with-android-architecture-components-part-2-testing-room-2b956deabc00  




