ChuckNorrisJoke
===================
This is the unattended test using the Chuck Norris Joke API for N Brown Group.
Test done by **Rafael Ruiz Muñoz**

How to build
-------------
> Just clone the GitHub repository and hit on **run**

Libraries used
-------------
> - **AppCompatLibrary** for Material Guidelines UI
> - **ButterKnife** for View Injection
> - **Retrofit 2** for HTTP calls
> - **RxJava 2** for better architecture, efficiency and clean code
> - **[FAB](https://github.com/Clans/FloatingActionButton)** for better dealing with FloatingActionButton and Menu

Architecture methodologies used
-------------
> - **[Uncle Bob Clean Architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html)** separating layers in **ui**, **domain** and **data**

Remarkable
-------------
> - **MVP** to show proof of knowledge of Model-View-Presenter. The model in this case was a `String` for a joke, so it's not included in the Model layer
> - Used **No explicit** to avoid that category from the API.
> - Handled loss of data when **rotation** or **split screen**.
