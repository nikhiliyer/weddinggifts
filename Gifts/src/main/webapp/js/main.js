var maxGifts = 10;
var minGifts = 1;

var progressMax = 0;
var progressVal = 0;

var totalPledged = 0;
var maxGiftVal = 0;

//call on ready
$(document).ready(function(){
	
	$("select").selectpicker({style: 'btn-hg btn-danger btn-md', menuStyle: 'dropdown-inverse'});
	$('.dropdown-toggle').removeClass('btn-hg');
	setSliderHt();
	//getWishList();
	getInfo();
	//loadGifted();
});

//call on resize
$(window).resize(function(){
	//getInfo();
	//setSliderHt();
})

function setSliderHt(){
	if( $('body').hasClass("gift") ){
		var leftContentHt = $('.content').outerHeight();
		//console.log(leftContentHt);
		$('.sidebar .progress').css("height",leftContentHt+"px");
		//setSliderProgress(0, 1);
		setGiftBoxes();
	}
}

function setSliderProgress(num, denom){
	
	console.log(num+", "+denom);
	
	 var percent =  Math.round( (num/denom)*100 );
	 $('.progress-bar').css("height",percent+"%");
	 $('.progress-bar').text(percent+'%');
	 setPin();
	 
	 //if statements to change the color
	 
	 var commonPinClass = 'floating pin pull-right ';
	 var commonProgressClass = 'progress-bar ';
	 var commonTextClass = 'text-info ';
	 
	 var txtRed = 'text-red';
	 var txtOrange = 'text-orange';
	 var txtYellow = 'text-yellow';
	 var txtGreen = 'text-green';
	 var txtBlue = 'text-blue';
	 
	 var pinRed = 'pin-red';
	 var pinOrange = 'pin-orange';
	 var pinYellow = 'pin-yellow';
	 var pinGreen = 'pin-green';
	 var pinBlue = 'pin-blue';

	 var progRed = 'prog-red';
	 var progOrange = 'prog-orange';
	 var progYellow = 'prog-yellow';
	 var progGreen = 'prog-green';
	 var progBlue = 'prog-blue';
	 
	 
	 
	 $('#pin').attr('class','');
	 $('#change-text').attr('class','');
	 $('#progbar').attr('class','');
	 
	 if(percent>=0 && percent<=20){//red	 
		 $('#change-text').text("Long way to go!");
		 $('#change-text').addClass(commonTextClass+txtRed);
		 $('#pin').addClass(commonPinClass+pinRed);
		 $('#progbar').addClass(commonProgressClass+progRed);
	 }
	 else if(percent>20 && percent<=30){//orange
		 $('#change-text').text("Not even half way there :(");
		 $('#change-text').addClass(commonTextClass+txtOrange);
		 $('#pin').addClass(commonPinClass+pinOrange);
		 $('#progbar').addClass(commonProgressClass+progOrange);
	 }
	 else if(percent>30 && percent<=50){//yellow
		 $('#change-text').text("Awesome! Half way there!");
		 $('#change-text').addClass(commonTextClass+txtYellow);
		 $('#pin').addClass(commonPinClass+pinYellow);
		 $('#progbar').addClass(commonProgressClass+progYellow);
	 }
	 else if(percent>50 && percent<=85){//green
		 $('#change-text').text("Nice! Keep going.");
		 $('#change-text').addClass(commonTextClass+txtGreen);
		 $('#pin').addClass(commonPinClass+pinGreen);
		 $('#progbar').addClass(commonProgressClass+progGreen);
	 }
	 else if(percent>85){//blue
		 $('#change-text').text("Woo hoo! Let's party!");
		 $('#change-text').addClass(commonTextClass+txtBlue);
		 $('#pin').addClass(commonPinClass+pinBlue);
		 $('#progbar').addClass(commonProgressClass+progBlue);
	 }
	 
}

function setPin(){
	//if( $('body').hasClass("gift") ){
		var progPos = $('.progress-bar').position();
		$('.info-text').css("top",progPos.top-10+"px");
		$('.pin').css("top",progPos.top-10+"px");
	//}
}

function setGiftBoxes(){
	
	var winHt = $(window).height();
	//console.log(winHt);
	
 	var gftsHt = $('.giftboxes').outerHeight();//get height of the outer div containing all giftboxes
 	var gftboxesHt = 0;
 	var progPos = $('.progress').position();
	$('.giftboxes').css("top",progPos+"px");
 	//console.log(progPos);
 	$('.giftbox').each(function(){
			gftboxesHt = gftboxesHt + $(this).outerHeight(); //cumulative total heights of each gift box
 	});
	$('.giftbox:last-child').css("top",gftsHt-gftboxesHt+15+"px");// assign positon of the last box to the bottom
	$('.giftboxes').css("top",progPos+"px");
}

function getWishList(){ //fetch Wish List JSON

	var jqxhr = $.getJSON( '/WeddingGifts/wishList.json', function(wishlist) {
	  console.log("acquired wishlist...");
	  maxGiftVal = wishlist.gifts[0].price;
	});
	
	jqxhr.complete(function(info) {
		maxGiftVal = wishlist.gifts[0].price;
		console.log(maxGiftVal);  
	});
}

function getInfo(){ //fetch Occasion Info JSON 
	
	var jqxhr = $.getJSON( '/WeddingGifts/occasionInfo.gift', function(info) {
	  console.log("acquired occasion info...");
	  //console.log(info.title);
	  	if( !$('body').hasClass("populated") ){
			$('body').addClass('populated');
			$('.starimage img').attr('src',info.imageUrl);
			$('.starabout').append(info.description);
		}
		totalPledged = info.totalContributions.totalAmountPledged;
		maxGiftVal = info.totalContributions.maxGiftPriceForOccasion
		setSliderProgress(totalPledged,maxGiftVal);
	});	
}

function pushData(){
	
    var str = $( "#giftForm" ).serialize();
    console.log(str);
	
	// $.ajax({
// 	  type: "POST",
// 	  url: "http://giftingdreams.com/WeddingGifts/addContribution.gift",
// 	  data: JSON.stringify({ occasionId: ,involvedPersonId: ,contactPersonId: ,name: ,phoneNumber: ,email: ,address: ,paymentMethod: ,amountPledged: ,customMessage:  }),
// 	  contentType: 'application/json',
// 	  success: function(data) {
// 	    if(data.status == 'OK') alert('Person has been added');
// 	    else alert('Failed adding person: ' + data.status + ', ' + data.errorMessage);
// 	  }
// 	});
	
}