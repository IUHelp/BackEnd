
var langs = [
		[ 'Afrikaans', [ 'af-ZA' ] ],
		[ 'Bahasa Indonesia', [ 'id-ID' ] ],
		[ 'Bahasa Melayu', [ 'ms-MY' ] ],
		[ 'Català', [ 'ca-ES' ] ],
		[ 'Čeština', [ 'cs-CZ' ] ],
		[ 'Deutsch', [ 'de-DE' ] ],
		[ 'English', [ 'en-AU', 'Australia' ], [ 'en-CA', 'Canada' ],
				[ 'en-IN', 'India' ], [ 'en-NZ', 'New Zealand' ],
				[ 'en-ZA', 'South Africa' ], [ 'en-GB', 'United Kingdom' ],
				[ 'en-US', 'United States' ] ],
		[ 'Español', [ 'es-AR', 'Argentina' ], [ 'es-BO', 'Bolivia' ],
				[ 'es-CL', 'Chile' ], [ 'es-CO', 'Colombia' ],
				[ 'es-CR', 'Costa Rica' ], [ 'es-EC', 'Ecuador' ],
				[ 'es-SV', 'El Salvador' ], [ 'es-ES', 'España' ],
				[ 'es-US', 'Estados Unidos' ], [ 'es-GT', 'Guatemala' ],
				[ 'es-HN', 'Honduras' ], [ 'es-MX', 'México' ],
				[ 'es-NI', 'Nicaragua' ], [ 'es-PA', 'Panamá' ],
				[ 'es-PY', 'Paraguay' ], [ 'es-PE', 'Perú' ],
				[ 'es-PR', 'Puerto Rico' ],
				[ 'es-DO', 'República Dominicana' ], [ 'es-UY', 'Uruguay' ],
				[ 'es-VE', 'Venezuela' ] ],
		[ 'Euskara', [ 'eu-ES' ] ],
		[ 'Français', [ 'fr-FR' ] ],
		[ 'Galego', [ 'gl-ES' ] ],
		[ 'Hrvatski', [ 'hr_HR' ] ],
		[ 'IsiZulu', [ 'zu-ZA' ] ],
		[ 'Íslenska', [ 'is-IS' ] ],
		[ 'Italiano', [ 'it-IT', 'Italia' ], [ 'it-CH', 'Svizzera' ] ],
		[ 'Magyar', [ 'hu-HU' ] ],
		[ 'Nederlands', [ 'nl-NL' ] ],
		[ 'Norsk bokmål', [ 'nb-NO' ] ],
		[ 'Polski', [ 'pl-PL' ] ],
		[ 'Português', [ 'pt-BR', 'Brasil' ], [ 'pt-PT', 'Portugal' ] ],
		[ 'Română', [ 'ro-RO' ] ],
		[ 'Slovenčina', [ 'sk-SK' ] ],
		[ 'Suomi', [ 'fi-FI' ] ],
		[ 'Svenska', [ 'sv-SE' ] ],
		[ 'Türkçe', [ 'tr-TR' ] ],
		[ 'български', [ 'bg-BG' ] ],
		[ 'Pусский', [ 'ru-RU' ] ],
		[ 'Српски', [ 'sr-RS' ] ],
		[ '한국어', [ 'ko-KR' ] ],
		[ '中文', [ 'cmn-Hans-CN', '普通话 (中国大陆)' ], [ 'cmn-Hans-HK', '普通话 (香港)' ],
				[ 'cmn-Hant-TW', '中文 (台灣)' ], [ 'yue-Hant-HK', '粵語 (香港)' ] ],
		[ '日本語', [ 'ja-JP' ] ], [ 'Lingua latīna', [ 'la' ] ] ];
for (var i = 0; i < langs.length; i++) {
	select_language.options[i] = new Option(langs[i][0], i);
}
select_language.selectedIndex = 6;
updateCountry();
select_dialect.selectedIndex = 6;
showInfo('info_start');

function updateCountry() {
	for (var i = select_dialect.options.length - 1; i >= 0; i--) {
		select_dialect.remove(i);
	}
	var list = langs[select_language.selectedIndex];
	for (var i = 1; i < list.length; i++) {
		select_dialect.options.add(new Option(list[i][1], list[i][0]));
	}
	select_dialect.style.visibility = list[1].length == 1 ? 'hidden'
			: 'visible';
}
var create_email = false;
var final_transcript = '';
var recognizing = false;
var ignore_onend;
var start_timestamp;

var randomUUID = generateUUID();

function generateUUID() {
  function s4() {
    return Math.floor((1 + Math.random()) * 0x10000)
      .toString(16)
      .substring(1);
  }
  return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
    s4() + '-' + s4() + s4() + s4();
}


/*
 * The default value for continuous is false, meaning that when the user stops
 * talking, speech recognition will end
 */
/*
 * The default value for interimResults is false, meaning that the only results
 * returned by the recognizer are final and will not change. The demo sets it to
 * true so we get early, interim results that may change. Watch the demo
 * carefully, the grey text is the text that is interim and does sometimes
 * change, whereas the black text are responses from the recognizer that are
 * marked final and will not change.
 */

if (!('webkitSpeechRecognition' in window)) {
	upgrade();
} else {
	start_button.style.display = 'inline-block';
	var recognition = new webkitSpeechRecognition();
	recognition.continuous = false;
	recognition.interimResults = true;
	recognition.onstart = function() {
		recognizing = true;
		showInfo('info_speak_now');
		start_img.src = 'images/mic-animate.gif';
	};
	recognition.onerror = function(event) {
		if (event.error == 'no-speech') {
			start_img.src = 'images/mic.gif';
			showInfo('info_no_speech');
			ignore_onend = true;
		}
		if (event.error == 'audio-capture') {
			start_img.src = 'images/mic.gif';
			showInfo('info_no_microphone');
			ignore_onend = true;
		}
		if (event.error == 'not-allowed') {
			if (event.timeStamp - start_timestamp < 100) {
				showInfo('info_blocked');
			} else {
				showInfo('info_denied');
			}
			ignore_onend = true;
		}
	};
	recognition.onend = function() {
		recognizing = false;
		if (ignore_onend) {
			return;
		}
		start_img.src = 'images/mic.gif';
		if (!final_transcript) {
			showInfo('info_start');
			return;
		}
		showInfo('');
		if (window.getSelection) {
			window.getSelection().removeAllRanges();
			var range = document.createRange();
			range.selectNode(document.getElementById('final_span'));
			window.getSelection().addRange(range);
		}
		if (create_email) {
			create_email = false;
			createEmail();
		}
	};
	recognition.onresult = function(event) {
		var interim_transcript = '';
		for (var i = event.resultIndex; i < event.results.length; ++i) {
			if (event.results[i].isFinal) {
				final_transcript += event.results[i][0].transcript;
			} else {
				interim_transcript += event.results[i][0].transcript;
			}
		}
		final_transcript = capitalize(final_transcript);
		final_span.innerHTML = linebreak(final_transcript);
		interim_span.innerHTML = linebreak(interim_transcript);
		if (final_transcript || interim_transcript) {
			showButtons('inline-block');
		}
	};
}
function upgrade() {
	start_button.style.visibility = 'hidden';
	showInfo('info_upgrade');
}
var two_line = /\n\n/g;
var one_line = /\n/g;
function linebreak(s) {
	return s.replace(two_line, '<p></p>').replace(one_line, '<br>');
}
var first_char = /\S/;
function capitalize(s) {
	return s.replace(first_char, function(m) {
		return m.toUpperCase();
	});
}
function createEmail() {
	var n = final_transcript.indexOf('\n');
	if (n < 0 || n >= 80) {
		n = 40 + final_transcript.substring(40).indexOf(' ');
	}
	var subject = encodeURI(final_transcript.substring(0, n));
	var body = encodeURI(final_transcript.substring(n + 1));
	window.location.href = 'mailto:?subject=' + subject + '&body=' + body;
}
function copyButton() {
	if (recognizing) {
		recognizing = false;
		recognition.stop();
	}
	copy_button.style.display = 'none';
	copy_info.style.display = 'inline-block';
	showInfo('');
}
function emailButton() {
	if (recognizing) {
		create_email = true;
		recognizing = false;
		recognition.stop();
	} else {
		createEmail();
	}
	email_button.style.display = 'none';
	email_info.style.display = 'inline-block';
	showInfo('');
}
function startButton(event) {

	if (recognizing) {
		recognition.stop();
		return;
	}
	final_transcript = '';
	recognition.lang = select_dialect.value;
	recognition.start();
	ignore_onend = false;
	final_span.innerHTML = '';
	interim_span.innerHTML = '';
	start_img.src = 'images/mic-slash.gif';
	showInfo('info_allow');
	showButtons('none');
	start_timestamp = event.timeStamp;
}
function showInfo(s) {
	if (s) {
		for (var child = info.firstChild; child; child = child.nextSibling) {
			if (child.style) {
				child.style.display = child.id == s ? 'inline' : 'none';
			}
		}
		info.style.visibility = 'visible';
	} else {
		info.style.visibility = 'hidden';
	}
}
var current_style;
function showButtons(style) {
	if (style == current_style) {
		return;
	}
	current_style = style;
//	copy_button.style.display = style;

//	email_button.style.display = style;

	//email_button.style.display = style;

	copy_info.style.display = 'none';
	email_info.style.display = 'none';
}

function httpGetAsync() {
	
	var json = JSON.stringify(final_transcript);
	 var link = null;
	 $("div").remove(".res");
	 $("#hello").collapse('hide');
     $("#searchbox").collapse('hide');
	console.log('sole');
	$.ajax({


		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		dataType : "json",
		type : "GET",
		url : "/search?searchQuery="+final_transcript+"&tempUserUUID="+randomUUID,
		data : json,
		error : function(xhr, err) {
			alert("readyState: " + xhr.readyState + "\nstatus: "
			+ xhr.status);
			alert("responseText: " + xhr.responseText + " " + err);
			console.log(xhr.responseText);
		},
		success : function(response) {

         console.log('result length --');
		 console.log(response.linksResult.length);
                if (response.linksResult.length == 0){

                                        $('<div class="res"> <div class="container-fluid"><h4>Could not fetch any results for your query. You can enter a new query or make the current query more specific </h4></div></div>').appendTo('#SearchResult');
                }else{

                      for (var i = 0; i < response.linksResult.length; i++) {

                                                       if (i==0){
                                                                                                              var maincontent = response.textResult;
                                                                                                              var length = maincontent.length;
                                                                                                              console.log(maincontent);
                                                                                                              var startIndex = maincontent.indexOf("<p");
                                                                                                                  if (startIndex >= 0){
                                                                                                                      maincontent = maincontent.substring(startIndex , length);
                                                                                                                  }else {
                                                                                                                      maincontent = "Whoooops !!!!! Couldn't find the matching text. May be you could try out these links ";
                                                                                                                  }
                                                                                                              console.log("main content---------------------------");
                                                                                                              console.log(maincontent);

                                                                                                   $('<div class="res"><div class="scroll-area"><div class="container-fluid" style="border:1px solid #cecece;">'+maincontent+'</div></div></div><br>').appendTo('#SearchResult');
                                                                                                   $('</div>').appendTo('#SearchResult');
                                                                                                   $('<div class="container-fluid"><ul class="list-group">').appendTo('#SearchResult');
                                                                                              }

                                                       link = response.linksResult[i];
                                                       /*var content = link.content ;
                                                       content = content.substring(300,500);*/

                                                       $('<div class="res"><li class="list-group-item"><a target="_blank" href='+link.link+'> <span class="tag tag-default tag-pill float-xs-right">'+link.score+'</span>'
                                                                          + link.title
                                                                          + '<br></a></li></div>').appendTo('#SearchResult');

                                                        /*$('</ul></div>');*/
                      }
                       $('</ul></div>').appendTo('#SearchResult');

                }

       }
	});
}



function httpGetAsyncText() {

	var json = JSON.stringify(document.getElementById("search").value);
	 var link = null;
	 var searchTerm = document.getElementById("search").value;
	 $("div").remove(".res");
	 $("div").remove("#seemore");

	 $("#hello").collapse('hide');
    // $("#searchbox").collapse('hide');
	console.log('sole');
	$.ajax({
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		dataType : "json",
		type : "GET",
		url : "/search?searchQuery="+searchTerm+"&tempUserUUID="+randomUUID,
		data : json,
		error : function(xhr, err) {
			alert("readyState: " + xhr.readyState + "\nstatus: "
			+ xhr.status);
			alert("responseText: " + xhr.responseText + " " + err);
			console.log(xhr.responseText);
		},
		success : function(response) {

                 console.log('result length --');
                 console.log(response);
               console.log(response.linksResult.length);

               if (response.linksResult.length == 0){

                        $('<div class="res"> <div class="container-fluid"><h4>Could not fetch any results for your query. You can enter a new query or make the current query more specific </h4></div></div>').appendTo('#SearchResult');
               }else{

                        for (var i = 0; i < response.linksResult.length; i++) {

                                       if (i==0){
                                                       var maincontent = response.textResult;
                                                       var length = maincontent.length;
                                                       console.log(maincontent);
                                                       var startIndex = maincontent.indexOf("<p");
                                                           if (startIndex >= 0){
                                                               maincontent = maincontent.substring(startIndex , length);
                                                           }else {
                                                               maincontent = "Whoooops !!!!! Couldn't find the matching text. May be you could try out these links ";
                                                           }
                                                       console.log("main content---------------------------");
                                                       console.log(maincontent);

                                            $('<div class="res"><div class="scroll-area"><div class="container-fluid" style="border:1px solid #cecece;">'+maincontent+'</div></div></div><br>').appendTo('#SearchResult');
                                            // $('</div>').appendTo('#SearchResult');
                                            $('<div class="container-fluid"><ul class="list-group">').appendTo('#SearchResult');
                                       }

                                       link = response.linksResult[i];
                                       /*var content = link.content ;
                                       content = content.substring(300,500);*/

                                       $('<div class="res"><li class="list-group-item"><a target="_blank" href='+link.link+'> <span class="tag tag-default tag-pill float-xs-right">'+link.score+'</span>'
                                                          + link.title
                                                          + '<br></a></li></div>').appendTo('#SearchResult');

                                        /*$('</ul></div>');*/
                        }
                        $('</ul></div>').appendTo('#SearchResult');

               }

        }

	});

	/*$('<div class="res"><div class="container-fluid"><button type="button" class="btn btn-warning" data-toggle="collapse" data-target="#info" aria-expanded="false" aria-controls="info">Matching result</button><br><div class="collapse" id ="info" style="color:#000000"><div>'+maincontent+'</div></div></div><br>').appendTo('#SearchResult');*/
}





