package com.phonecontact_demo;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Random;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
	private static ListView contact_listview;
	private static ArrayList<Contact_Model> arrayList;
	private static Contact_Adapter adapter;

	private static ProgressDialog pd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		contact_listview = (ListView) findViewById(R.id.contact_listview);

		new LoadContacts().execute();// Execute the async task

	}

	// Async task to load contacts
	private class LoadContacts extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			arrayList = readContacts();// Get contacts array list from this
										// method
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			super.onPostExecute(result);

			// If array list is not null and is contains value
			if (arrayList != null && arrayList.size() > 0) {

				// then set total contacts to subtitle
				getSupportActionBar().setSubtitle(
						arrayList.size() + " Contacts");
				adapter = null;
				if (adapter == null) {
					adapter = new Contact_Adapter(MainActivity.this, arrayList);
					contact_listview.setAdapter(adapter);// set adapter
				}
				adapter.notifyDataSetChanged();
			} else {

				// If adapter is null then show toast
				Toast.makeText(MainActivity.this, "There are no contacts.",
						Toast.LENGTH_LONG).show();
			}

			// Hide dialog if showing
			if (pd.isShowing())
				pd.dismiss();

		}

		@Override
		protected void onPreExecute() {

			super.onPreExecute();
			// Show Dialog
			pd = ProgressDialog.show(MainActivity.this, "Loading Contacts",
					"Please Wait...");
		}

	}

	// Method that return all contact details in array format
	private ArrayList<Contact_Model> readContacts() {
		ArrayList<Contact_Model> contactList = new ArrayList<Contact_Model>();

		Uri uri = ContactsContract.Contacts.CONTENT_URI; // Contact URI
		Cursor contactsCursor = getContentResolver().query(uri, null, null,
				null, ContactsContract.Contacts.DISPLAY_NAME + " ASC "); // Return
																			// all
																			// contacts
																			// name
																			// containing
																			// in
																			// URI
																			// in
																			// ascending
																			// order
		// Move cursor at starting
		if (contactsCursor.moveToFirst()) {
			do {
				long contctId = contactsCursor.getLong(contactsCursor
						.getColumnIndex("_ID")); // Get contact ID
				Uri dataUri = ContactsContract.Data.CONTENT_URI; // URI to get
																	// data of
																	// contacts
				Cursor dataCursor = getContentResolver().query(dataUri, null,
						ContactsContract.Data.CONTACT_ID + " = " + contctId,
						null, null);// Retrun data cusror represntative to
									// contact ID

				// Strings to get all details
				String displayName = "";
				String nickName = "";
				String homePhone = "";
				String mobilePhone = "";
				String workPhone = "";
				String photoPath = "" + R.drawable.ic_launcher; // Photo path
				byte[] photoByte = null;// Byte to get photo since it will come
										// in BLOB
				String homeEmail = "";
				String workEmail = "";
				String companyName = "";
				String title = "";

				// This strings stores all contact numbers, email and other
				// details like nick name, company etc.
				String contactNumbers = "";
				String contactEmailAddresses = "";
				String contactOtherDetails = "";

				// Now start the cusrsor
				if (dataCursor.moveToFirst()) {
					displayName = dataCursor
							.getString(dataCursor
									.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));// get
																								// the
																								// contact
																								// name
					do {
						if (dataCursor
								.getString(
										dataCursor.getColumnIndex("mimetype"))
								.equals(ContactsContract.CommonDataKinds.Nickname.CONTENT_ITEM_TYPE)) {
							nickName = dataCursor.getString(dataCursor
									.getColumnIndex("data1")); // Get Nick Name
							contactOtherDetails += "NickName : " + nickName
									+ "\n";// Add the nick name to string

						}

						if (dataCursor
								.getString(
										dataCursor.getColumnIndex("mimetype"))
								.equals(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)) {

							// In this get All contact numbers like home,
							// mobile, work, etc and add them to numbers string
							switch (dataCursor.getInt(dataCursor
									.getColumnIndex("data2"))) {
							case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
								homePhone = dataCursor.getString(dataCursor
										.getColumnIndex("data1"));
								contactNumbers += "Home Phone : " + homePhone
										+ "\n";
								break;

							case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
								workPhone = dataCursor.getString(dataCursor
										.getColumnIndex("data1"));
								contactNumbers += "Work Phone : " + workPhone
										+ "\n";
								break;

							case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
								mobilePhone = dataCursor.getString(dataCursor
										.getColumnIndex("data1"));
								contactNumbers += "Mobile Phone : "
										+ mobilePhone + "\n";
								break;

							}
						}
						if (dataCursor
								.getString(
										dataCursor.getColumnIndex("mimetype"))
								.equals(ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)) {

							// In this get all Emails like home, work etc and
							// add them to email string
							switch (dataCursor.getInt(dataCursor
									.getColumnIndex("data2"))) {
							case ContactsContract.CommonDataKinds.Email.TYPE_HOME:
								homeEmail = dataCursor.getString(dataCursor
										.getColumnIndex("data1"));
								contactEmailAddresses += "Home Email : "
										+ homeEmail + "\n";
								break;
							case ContactsContract.CommonDataKinds.Email.TYPE_WORK:
								workEmail = dataCursor.getString(dataCursor
										.getColumnIndex("data1"));
								contactEmailAddresses += "Work Email : "
										+ workEmail + "\n";
								break;

							}
						}

						if (dataCursor
								.getString(
										dataCursor.getColumnIndex("mimetype"))
								.equals(ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)) {
							companyName = dataCursor.getString(dataCursor
									.getColumnIndex("data1"));// get company
																// name
							contactOtherDetails += "Coompany Name : "
									+ companyName + "\n";
							title = dataCursor.getString(dataCursor
									.getColumnIndex("data4"));// get Company
																// title
							contactOtherDetails += "Title : " + title + "\n";

						}

						if (dataCursor
								.getString(
										dataCursor.getColumnIndex("mimetype"))
								.equals(ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE)) {
							photoByte = dataCursor.getBlob(dataCursor
									.getColumnIndex("data15")); // get photo in
																// byte

							if (photoByte != null) {

								// Now make a cache folder in file manager to
								// make cache of contacts images and save them
								// in .png
								Bitmap bitmap = BitmapFactory.decodeByteArray(
										photoByte, 0, photoByte.length);
								File cacheDirectory = getBaseContext()
										.getCacheDir();
								File tmp = new File(cacheDirectory.getPath()
										+ "/_androhub" + contctId + ".png");
								try {
									FileOutputStream fileOutputStream = new FileOutputStream(
											tmp);
									bitmap.compress(Bitmap.CompressFormat.PNG,
											100, fileOutputStream);
									fileOutputStream.flush();
									fileOutputStream.close();
								} catch (Exception e) {
									// TODO: handle exception
									e.printStackTrace();
								}
								photoPath = tmp.getPath();// finally get the
															// saved path of
															// image
							}

						}

					} while (dataCursor.moveToNext()); // Now move to next
														// cursor

					contactList.add(new Contact_Model(Long.toString(contctId),
							displayName, contactNumbers, contactEmailAddresses,
							photoPath, contactOtherDetails));// Finally add
																// items to
																// array list
				}

			} while (contactsCursor.moveToNext());
		}
		return contactList;
	}

}
