# Stick To Them


### Phase 1: Register app to Firebase
- Register app on firebase console
- Download google services file
- Put google services file in the right place and add dependencies as instructed

### Phase 2: Set up Firebase Realtime Database
- Create database
- add more imports in the gradle:app
    `implementation fileTree(dir: "libs", include: ["*.jar"])`
- Get the reference of the root of the JSON Tree
    `DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();`

### Phase 3: Set up the structure of the Database
- It is basically a list objects
- Create User object
- Create Login function in Main activity
- Set up username as a global field
- Use Login method to check whether the new user is exist in database or not

### Phase 4: Create other Activities
- MainActivity: Log in and landing page
- GetUsersActivity: list of the users in this activity so that we can select one to send the notification
- SendMessageActivity: We can select the image and input the text that we want to send to the specific user
- ReceiveMessageActivity: A page that can present the notification we received

### Phase 5: 