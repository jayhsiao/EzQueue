window.fbAsyncInit = function() {
	FB.init({
		appId : '554860634671080',
		cookie : true, // enable cookies to allow the server to access
		// the session
		xfbml : true, // parse social plugins on this page
		version : 'v2.5' // use graph api version 2.5
	});

	// Now that we've initialized the JavaScript SDK, we call
	// FB.getLoginStatus(). This function gets the state of the
	// person visiting this page and can return one of three states to
	// the callback you provide. They can be:
	//
	// 1. Logged into your app ('connected')
	// 2. Logged into Facebook, but not your app ('not_authorized')
	// 3. Not logged into Facebook and can't tell if they are logged into
	// your app or not.
	//
	// These three cases are handled in the callback function.

	FB.getLoginStatus(function(response) {
		statusChangeCallback(response);
	});
};

// Load the SDK asynchronously
(function(d, s, id) {
	var js, fjs = d.getElementsByTagName(s)[0];
	if (d.getElementById(id))
		return;
	js = d.createElement(s);
	js.id = id;
	js.src = "//connect.facebook.net/zh_TW/sdk.js";
	fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

//This is called with the results from from FB.getLoginStatus().
function statusChangeCallback(response) {
	// The response object is returned with a status field that lets the
	// app know the current login status of the person.
	// Full docs on the response object can be found in the documentation
	// for FB.getLoginStatus().
	if (response.status === 'connected') {
		// Logged into your app and Facebook.
		mainObj.login();
	} else if (response.status === 'not_authorized') {
		// The person is logged into Facebook, but not your app.
		mainObj.noLogin();
	} else {
		// The person is not logged into Facebook, so we're not sure if
		// they are logged into this app or not.
		mainObj.noLogin();
	}
}

// This function is called when someone finishes with the Login
// Button. See the onlogin handler attached to it in the sample
// code below.
function checkLoginState() {
	FB.getLoginStatus(function(response) {
		statusChangeCallback(response);
	});
}

var facebookObj = {
	
	id: null,
	password: null,
	
	getProfile: function(){
		FB.api(
			"/me?fields=id,name,email,accounts{app_id,name,is_verified}",
		    function (response) {
		    	if (response && !response.error) {
		    		/* handle the result */
		    		facebookObj.id = response.id;
		    		
		    		var body = {
	    				id: response.id,
	    				name: response.name, 
	    				email: response.email,
	    				accounts: response.accounts
	    			};
		    		
		    		ajaxUtilObj.registration(JSON.stringify(body))
		    		.done(function(user){
		    			$("#img_header_facebook_user").attr("src", "http://graph.facebook.com/"+user.facebookId+"/picture?width=12&height=12");
		    			$("#span_header_facebook_user_name").text(user.name);
		    			mainObj.showLogin(user);
		    		});
		    		
		    		ajaxUtilObj.login(facebookObj.id, facebookObj.password);
		    	}
		    }
	    );
	}, 
	
	logout: function(){
		FB.logout(function(response) {
			facebookObj.id = "";
			$("#input_userId").val("");
			window.location = "/ezqueue/home";
		});
	}
}
