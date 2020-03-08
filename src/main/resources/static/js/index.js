$(document).ready(function(){	
	var token;
	var isAllPosts=true;
	var postPage=0;
	var feedPage=0;
	if(document.cookie.includes("accesstoken")) {
		token = document.cookie.split('token=')[1];
		isAllPosts = false;
	}
	
	function getPost(){
		$.ajax({
			beforeSend: function(xhr){
				xhr.setRequestHeader('accesstoken', token);
	        },
	        url: "/post"+"?page="+postPage
	    }).then(function(data) {
	    	$.each(data.data.post, function(index, e) {
	    		$('#posts').append(
	    				'<div class="card mb-4"> <div class="card-body"> <h2 class="card-title">' + e.title 
	    				+ '</h2> <p class="card-text">' + e.content 
	    				+ '</p> <a href="/post/detail/' + e.id 
	    				+ '" class="btn btn-primary">Read More &rarr;</a> </div> ' 
	    				+ '<div class="card-footer text-muted"> Posted on ' + e.createdAt.split('T')[0]
	    				+ ' by ' + e.user.username + getFollowInfo(e.user)
	    				+ ' views ' + e.views
	    				+ '</div> </div>');
	    	});
	    	
	    	if(data.data.hasNextPage){
	    		postPage++;
	    	}else{
	    		postPage=-1;
	    	}
	    	nextButtonView(postPage);
	       console.log(data);
	    }, function(err) {
	    	console.log(err.responseJSON);
	    });
	}
	
	function getFeed(){
		if(token) {
			$.ajax({
				beforeSend: function(xhr){
					xhr.setRequestHeader('accesstoken', token);
		        },
		        url: "/post/feed"+"?page="+feedPage
		    }).then(function(data) {
		    	$.each(data.data.post, function(index, e) {
		    		$('#myfeed').append(
		    				'<div class="card mb-4"> <div class="card-body"> <h2 class="card-title">' + e.title 
		    				+ '</h2> <p class="card-text">' + e.content 
		    				+ '</p> <a href="/post/detail/' + e.id 
		    				+ '" class="btn btn-primary">Read More &rarr;</a> </div> ' 
		    				+ '<div class="card-footer text-muted"> Posted on ' + e.createdAt.split('T')[0]
		    				+ ' by ' + e.user.username + getFollowInfo(e.user)
		    				+ ' views ' + e.views
		    				+ '</div> </div>');
		    	});
		    	
		    	if(data.data.hasNextPage){
		    		feedPage++;
		    	}else{
		    		feedPage=-1;
		    	}
		    	nextButtonView(feedPage);
		       console.log(data);
		    }, function(err) {
		    	console.log(err.responseJSON);
		    });
		}
	}
	
	function getFollowInfo(user) {
		if(user.isFollow) {
			return ' <span class="unfollow" value="' + user.id + '" style="color:blue; cursor: pointer;"> Unfollow </span>';	
		} else if(user.isFollow == null){
			return '';
		} else {
			return ' <span class="follow" value="' + user.id + '" style="color:blue; cursor: pointer;"> Follow </span>';
		}
		
	}
	
	function nextButtonView(page){
		if( page >= 0){
			$('#pageButton').show();
		}else{
			$('#pageButton').hide();
		}
	}
	
	$('a[href="#myfeed"]').on("click", function(){
		nextButtonView(feedPage);
		isAllPosts=false;
	});
	$('a[href="#posts"]').on("click", function(){
		nextButtonView(postPage);
		isAllPosts=true;
	});
	
	$('#pageButton').on("click", function(){
		if(isAllPosts){
			getPost();
		}else{
			getFeed();
		}
	});
	
	getPost();
	getFeed();
	
	$('#save_post_btn').click(function(){
		var title = $('#create_title_text').val();
		var content = $('#create_content_text').val();
		
		console.log(title);
		console.log(content);
		
		var param = {
			title: title,
			content: content
		}
		
		$.ajax({
			beforeSend: function(xhr){
				xhr.setRequestHeader('accesstoken', token);
	        },
	        url: "/post",
	        method: "POST",
	        dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(param)
	    }).then(function(data) {
	    	window.location.href = '/';
	    }, function(err) {
	    	alert(err.responseJSON);
	    });
	});
	
	$('#header_logout_btn').click(function(){
		document.cookie = "accesstoken=; expires=Thu, 01 Jan 1970 00:00:01 GMT;";
		document.cookie = "userId=; expires=Thu, 01 Jan 1970 00:00:01 GMT;";
		window.location.href = '/';
	});
	
	$('body').on('click', '.follow', function($event) {
		console.log($(event.target).html());
		console.log($(this).html());
		console.log($(this).attr('value'));
		var userId = $(this).attr('value');
		
		var param = {
			followeeId: userId
		}
		
		$.ajax({
			beforeSend: function(xhr){
				xhr.setRequestHeader('accesstoken', token);
	        },
	        url: "/follow",
	        method: "POST",
	        dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(param)
	    }).then(function(data) {
	    	window.location.reload(); 	    
	    }, function(err) {
	    	alert(err.responseJSON);
	    });
	});
	
	$('body').on('click', '.unfollow', function() {
		console.log("unfollow clicked!!!");
		
		var userId = $(this).attr('value');
		
		var param = {
			followeeId: userId
		}
		
		$.ajax({
			beforeSend: function(xhr){
				xhr.setRequestHeader('accesstoken', token);
	        },
	        url: "/follow",
	        method: "DELETE",
	        dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(param)
	    }).then(function(data) {
	    	window.location.reload();
	    }, function(err) {
	    	alert(err.responseJSON);
	    });
	});
	
});
