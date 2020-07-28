# Auth 
* Not protected
## Post /api/auth/signup
    Body:
    {
        "firstName": "bill",
        "username": "user123",
        "email": "example@example.com",
        "password": "123456",
        "lastName": "zgantz",
        "dob: "2020-07-28T09:48:27.017Z",
        "gender": "other" 
    }
    
 ## Post /api/auth/signin 
    Body: 
    {
      "password": "string",
      "usernameOrEmail": "string"
    }
    Returns: 
    {
        "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI1ZGE1OTg2MGIwYzllODM1NGVjMDUyOTYiLCJpYXQiOjE1OTU5Mjk5NTEsImV4cCI6MTU5NjUzNDc1MSwicm9sZSI6ImFkbWluIiwidXNlcklkIjoiNWRhNTk4NjBiMGM5ZTgzNTRlYzA1Mjk2In0.cm_Qy6PCWy9gMn8nnUId1efZq2_aQCindtvkpQwEwDpMVH2pxuxMokqszlWpCP2-35iqJz8iVUoq2lq8ZpVxqw",
        "tokenType": "Bearer"
    }
    
 # User 
 * Protected, needs Authorization
 
 ## update user PUT /api/users/update
    To update a user the whole user object is required, 
    front end already has it probably
    returns a 200 status and the User object on successfull update
    Body:
    {
      "age": 0,
      "dateCreated": "2020-07-28T11:28:45.946Z",
      "dob": "2020-07-28T11:28:45.946Z",
      "email": "string",
      "favourites": [
        {
          "name": "string",
          "placeId": "string"
        }
      ],
      "firstName": "string",
      "gender": "string",
      "id": "string",
      "lastName": "string",
      "password": "string",
      "priceLevel": 0,
      "role": "string",
      "roles": [
        {
          "id": "string",
          "name": "ROLE_USER"
        }
      ],
      "types": {
        "bar": 0,
        "cafe": 0,
        "establishment": 0,
        "food": 0,
        "gym": 0,
        "health": 0,
        "lodging": 0,
        "pointOfInterest": 0,
        "restaurant": 0
      },
      "username": "string"
    }
 
## first time log in GET /api/users/first
    In case the user hasnt added any places to his account yet
    returns: 
    {
    "firstLogin": True/False
    }

## get logged in user GET /api/users/me
    returns: 
    {
        "id": "5da59860b0c9e8354ec05296",
        "firstName": null,
        "username": "billzg",
        "email": "123456789@gmail.com",
        "password": "$2a$10$JFmTN5Wgj1GFI1bkwzxEFONH9aX/Pg1mCEtzKPz5EFLyW//XB8lBe",
        "roles": [
            {
                "id": "5da59858b0c9e8354ec05295",
                "name": "ROLE_USER"
            }
        ],
        "lastName": "zgantz",
        "role": "admin",
        "gender": null,
        "dateCreated": null,
        "age": 0,
        "priceLevel": 0,
        "types": null,
        "dob": null
    }
    
# places

* Protected, needs Authorization

## Get single place
    GET /api/places/{placeId}
    returns: 
    {
        "id": "a839ac2394f4e337785ba34bd863ffb7e5684327",
        "geometry": {
            "location": {
                "lat": 37.9859959,
                "lng": 23.7302007
            },
            "viewport": {
                "northeast": {
                    "lat": 37.9872955302915,
                    "lng": 23.73153988029151
                },
                "southwest": {
                    "lat": 37.9845975697085,
                    "lng": 23.7288419197085
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/lodging-71.png",
        "name": "Meliá Athens",
        "rating": 4.3,
        "reference": "ChIJDSSprTG9oRQRWcYwsx3jeOI",
        "scope": "GOOGLE",
        "types": [
            "lodging",
            "restaurant",
            "food",
            "point_of_interest",
            "establishment"
        ],
        "vicinity": "Chalkokondili 14 & 28th October, Athina",
        "opening_hours": {
            "open_now": true
        },
        "place_id": "ChIJDSSprTG9oRQRWcYwsx3jeOI",
        "price_level": 0,
        "user_ratings_total": 1171
    }
    

## Get all places
    GET /api/places/all
    returns:
    [
    {},
    {},
    {},
    .
    .
    .
    .
    ]
## Do we need more here?


# Search Controller

## advanced search POST /api/search/advanced
    Body:
    {
      "age": 0,
      "bar": 0,
      "cafe": 0,
      "establishment": 0,
      "food": 0,
      "gender": "string",
      "genderNumeric": 0,
      "gym": 0,
      "health": 0,
      "lodging": 0,
      "pointOfInterest": 0,
      "priceLevel": 0,
      "restaurant": 0
    }
    returns: TBD

## quick search POST /api/search/quick
    returns: TBD