# Read Phone Contacts Using Content Provider
You can read phone contacts using [Content Provider](http://developer.android.com/intl/ru/guide/topics/providers/content-providers.html). With the help of this content provider we can communicate with the contacts app and query the URIs  and columns to get contact information and it will return as contact name, all contact numbers, all email addresses, company name, etc.
<br>
Content providers manage access to a structured set of data. They encapsulate the data, and provide mechanisms for defining data security. Content providers are the standard interface that connects data in one process with code running in another process. A content provider is only required if you need to share data between multiple applications. For example, the contacts data is used by multiple applications and must be stored in a content provider. If you don’t need to share data amongest multiple applications you can use a database directly via [SQLite Database](http://androhub.com/android-sqlite-database/).
<br>
When you want to access data in a content provider, you use the [ContentResolver](http://developer.android.com/intl/ru/reference/android/content/ContentResolver.html) object in your application’s Context to communicate with the provider as a client. The ContentResolver object communicates with the provider object, an instance of a class that implements ContentProvider. The provider object receives data requests from clients, performs the requested action, and returns the results.

For detailed explanation of code, [visit here](http://www.androhub.com/android-read-contacts-using-content-provider/).

# Video Demo
👉 Watch it <a href="https://youtu.be/G0qObXv8Tbg">here</a>.
<br>

[![Watch demo](http://i3.ytimg.com/vi/G0qObXv8Tbg/hqdefault.jpg)](https://youtu.be/G0qObXv8Tbg)

# Related

- [Content Provider](http://www.androhub.com/android-content-provider/)
- [SQLite Database](http://www.androhub.com/android-sqlite-database/)

# Connect with Us
- <a href="https://www.youtube.com/channel/@Androhub" target="_blank">`Youtube`</a>
- <a href="https://www.facebook.com/androhubtutorial/" target="_blank">`Facebook`</a>
- <a href="https://www.instagram.com/androhub_tutorial" target="_blank">`Instagram`</a>
- <a href="https://www.linkedin.com/in/surender-kumar-681472a8?originalSubdomain=in" target="_blank">`LinkedIn `</a>
- <a href="https://twitter.com/sonusurender0/" target="_blank">`Twitter`</a>
- <a href="http://www.androhub.com/" target="_blank">`Website`</a>

# Support
Reach out to me at surender@androhub.com.

# Buy Me a Coffee
Donate for the free content.

<a href="https://www.buymeacoffee.com/androhub" target="_blank"><img src="https://cdn.buymeacoffee.com/buttons/v2/default-yellow.png" alt="Buy Me A Coffee" style="height: 60px !important;width: 217px !important;" ></a>
