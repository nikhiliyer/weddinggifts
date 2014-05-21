var maxGifts = 10;
var minGifts = 1;

var progressMax = 0;
var progressVal = 0;

var totalPledged = 0;
var maxGiftVal = 0;

var giftsArray;

//call on ready
$(document).ready(function(){
 	
	 
	
	if($('body').hasClass('gift')){
		countdown();
		$("select").selectpicker({style: 'btn-hg btn-danger btn-md', menuStyle: 'dropdown-inverse'});
		$('.dropdown-toggle').removeClass('btn-hg');
		getWishList();
	}
 
    if( !$('body').hasClass('success')){
		setSliderHt();
		getInfo();
	}
	
	$(':radio').on('toggle', function() {
	  var radClass = '';
	  radClass = $('input:radio[name=payment]:checked').val();
	  //alert(radClass);
	  $('.payment-info').removeClass('active');
	  $('.'+radClass).addClass('active');
	});
	
	$('.backg').css("height",$(window).height()+"px");
	
});

//call on resize
$(window).resize(function(){
	$('.backg').css("height",$(window).height()+"px");
	//getInfo();
	//setSliderHt();
});

function countdown(){
	
    var $countdown = $('#countdown'),
        dateCountdown = $countdown.data('countdown'),
        endCountdownMSg = 'Let us party!',
        countdownComp = {
            weeks   : '<div class="count-el"><span>%-w</span> %!w:week,weeks;</div>',
            days    : '<div class="count-el"><span>%-d</span> %!d:day,days;</div>',
            hours   : '<div class="count-el"><span>%-H</span> %!H:hour,hours;</div>',
            mins    : '<div class="count-el"><span>%-M</span> %!M:minute,minutes;</div>',
            sec     : '<div class="count-el"><span>%-S</span> %!S:second,seconds;</div>'
        };
    
    $countdown.countdown(dateCountdown)
    .on('update.countdown', function(event) {
        var format = '';      
        if(event.offset.weeks > 0) {
            format += countdownComp.weeks;
        }
        format += countdownComp.days
                + countdownComp.hours
                + countdownComp.mins
                + countdownComp.sec;

        $(this).html(event.strftime(format));
    })
    .on('finish.countdown', function(event) {
        $(this).addClass('end-countdown').html('<span>'+endCountdownMSg+'</span>');
    });
	
}

function setSliderHt(){
	if( $('body').hasClass("gift") ){
		var leftContentHt = $('.content').outerHeight();
		//console.log(leftContentHt);
		$('.sidebar .progress').css("height",leftContentHt+"px");
		$('.giftboxes').css("height",leftContentHt+"px");
		$('.pin').css("top",leftContentHt-10+"px");
		
		// var sliderPos = $('.progress').position()
// 		$('.giftboxes').css("top",sliderPos.top+"px");
		
		//setGiftBoxes();
	}
}

function setSliderProgress(num, denom){
	
	 //console.log(num+", "+denom);
	
	 var percent =  Math.round( (num/denom)*100 );
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
		 
		 $('#change-text').text("Rs."+numeral(num).format('0,0')+" Long way to go!");
		 $('#change-text').addClass(commonTextClass+txtRed);
		 $('#pin').addClass(commonPinClass+pinRed);
		 $('#progbar').addClass(commonProgressClass+progRed);
	 }
	 else if(percent>20 && percent<=45){//orange
		 $('#change-text').text("Rs. "+numeral(num).format('0,0')+" Not even half way there :'(");
		 $('#change-text').addClass(commonTextClass+txtOrange);
		 $('#pin').addClass(commonPinClass+pinOrange);
		 $('#progbar').addClass(commonProgressClass+progOrange);
	 }
	 else if(percent>45 && percent<=50){//yellow
		 $('#change-text').text("Rs. "+numeral(num).format('0,0')+" Awesome! Half way there!");
		 $('#change-text').addClass(commonTextClass+txtYellow);
		 $('#pin').addClass(commonPinClass+pinYellow);
		 $('#progbar').addClass(commonProgressClass+progYellow);
	 }
	 else if(percent>50 && percent<=85){//green
		 $('#change-text').text("Rs. "+numeral(num).format('0,0')+" Nice! Keep going.");
		 $('#change-text').addClass(commonTextClass+txtGreen);
		 $('#pin').addClass(commonPinClass+pinGreen);
		 $('#progbar').addClass(commonProgressClass+progGreen);
	 }
	 else if(percent>85){//blue
		 $('#change-text').text("Rs. "+numeral(num).format('0,0')+" Woo hoo! Let's party!");
		 $('#change-text').addClass(commonTextClass+txtBlue);
		 $('#pin').addClass(commonPinClass+pinBlue);
		 $('#progbar').addClass(commonProgressClass+progBlue);
	 }
	 
	 if(percent<=100){
	 	$('.progress-bar').css("height",percent+"%");
	 }
	 else{
	 	$('.progress-bar').css("height",100+"%");
	 }
	 
	 $('.progress-bar').text(percent+'%');
	 setPin(); //set the pin position
	 
 	$('.giftbox').each(function(){
		//console.log($(this).attr("data-value"));
		if( $(this).attr("data-value") <= num && !$(this).parent().hasClass('stickered') ){
			$(this).parent().addClass('stickered');
			//change border color
			$(this).addClass('available');
			//add sticker
			$(this).append('<span class="sticker">available!</span>');
		}
 	});
	 
}

function setPin(){
	if( $('body').hasClass("gift") ){
		var progPos = $('.progress-bar').position();
		$('.info-text').css("top",progPos.top-10+"px");
		$('.pin').css("top",progPos.top-10+"px");
	}
}

function setGiftBoxes(){
	
	if( !$('.giftboxes').hasClass('set') ){
		$('.giftboxes').addClass('set');
		
	}
}

function getWishList(){ //fetch Wish List JSON

	var jqxhr = $.getJSON( '/WeddingGifts/wishList.gift', function(wishlist) {
	  console.log("acquired wishlist...");		
		giftsArray = new Array(wishlist.gifts.length);
		
		for(var i=0, l=wishlist.gifts.length; i<l; i++){			
			$('.giftboxes').append('<div style="bottom:" class="giftbox clearfix" data-value="'+wishlist.gifts[i].price+'"><div class='+'"pull-left giftimg"'+'><img src='+'"'+wishlist.gifts[i].imageUrl+'"'+'></div><div class='+'"pull-left gifttitle"'+'><h6>'+wishlist.gifts[i].name+', Rs. '+numeral(wishlist.gifts[i].price).format('0,0')+'</h6><p>'+wishlist.gifts[i].description+'</p></div></div>');	
			
		}
		
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
			$('#occid').val(info.id);
			var introTxt = $('.starabout p:first-child').text();
			var endTxt = $('.starabout p:last-child').text();
			$('.starabout p:first-child').text('');
			$('#intro-fat').append(introTxt.replace(' and ',' &amp; '));//replace and with &
			$('#intro-fat').text(introTxt.replace(',',''));//replace , at the end of the sentence with nothing
			
			//fetch comments data
			for(var i=0, l=info.someComments.length; i<l & i<3; i++){
				$('#comments').append('<div class='+'"comments-block"'+'><h6>'+info.someComments[i].name+'</h6><p>'+info.someComments[i].customMessage+'</p></div>');
			}
			//fetch contacts data
			for(var i=0, l=info.contacts.length; i<l; i++){
				var contactImg = '';
				if(info.contacts[i].imageUrl==null || info.contacts[i].imageUrl==''){contactImg='images/user.svg'}else{contactImg=info.contacts[i].imageUrl}
				$('#contact-people').append('<div class='+'"invld-people clearfix"'+'><div class='+'"pull-left perimg"'+'><img src='+'"'+contactImg+'"'+'/></div><div class='+'"pull-left pername"'+'><h6 class='+'"name"'+'>'+info.contacts[i].name+'</h6><h6 class='+'"num"'+'>'+info.contacts[i].phoneNumber+'</h6><h6 class='+'"num"'+'>'+info.contacts[i].email+'</h6></div></div>');
				$('#people').append('<option value='+info.contacts[i].id+'>'+info.contacts[i].name+'</option>');
			}
			//fetch wedding and event details
			for(var i=0, l=info.events.length; i<l; i++){
				
				$('.weddingdetails').append('<h6 class='+'"occ-title"'+'>'+info.events[i].name+'</h6><p class='+'"occ-desc"'+'>on '+info.events[i].date+'</p><p class='+'"occ-desc"'+'>'+info.events[i].time+' onwards</p><p> at '+info.events[i].location+'</p><div class='+'"separator"'+'></div>');
			}		
		}
			totalPledged = info.totalContributions.totalAmountPledged;
			maxGiftVal = info.totalContributions.maxGiftPriceForOccasion;
			setSliderProgress(totalPledged,maxGiftVal);
		
	});	
}

$( "#giftForm" ).submit(function( event ) {
	
	//console.log($('#giftForm').parsley().validate());
	//console.log($("#people option:selected").val());

	if ( $('#giftForm').parsley().validate() )
	{
		console.log(JSON.stringify({ occasionId:$("#occid").val() ,contactPersonId: $("#people").val(), name: $("#name").val(), phoneNumber:$("#phone").val() ,email:$("#email").val() ,address:$("#address").val() ,paymentMethod: $('input:radio[name=payment]:checked').val() ,amountPledged:$('#amount').val() ,customMessage:$('#message').val() }));
		event.preventDefault();
		$.ajax({
		  type: "POST",
		  url: "/WeddingGifts/addContribution.gift",
		  data: JSON.stringify({ occasionId:$("#occid").val() ,contactPersonId: $("#people").val(), name: $("#name").val(), phoneNumber:$("#phone").val() ,email:$("#email").val() ,address:$("#address").val() ,paymentMethod: $('input:radio[name=payment]:checked').val() ,amountPledged:$('#amount').val() ,customMessage:$('#message').val()  }),
		  contentType: 'application/json',
		  success: function(data) {
		    if(data.status == 'Success'){
				console.log("Amount successfully pledged");
				window.location.href = "success.html";
			}
		    else{
				console.log("Error");
				alert('Error adding gift: ' + data.status + ', ' + data.errorMessage);
				window.location.href = "index.html";
			}
		  }
		});
		//window.location.href = "success.html";
	}
});