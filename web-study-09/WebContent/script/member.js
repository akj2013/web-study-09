/*
 * 자바 스크립트와 자바의 차이점
 * 
 * 자바는 문장이 끝나면 반드시 세미콜론으로 마무리 해야 하지만 자바스크립트는 세미콜론을 생략할 수 있습니다.
 * 자바는 작은따옴표에 둘러싸인 것을 문자로, 큰 따옴표에 둘러싸인 것을 문자열로 인식하여 둘이 서로 다른 의미로 파악하지만 자바스크립트에서는 둘 다 문자열로 인식하여 동일한 의미로 파악합니다.
 * 변수를 선언할 때 특정 자료 형태 없이 var를 사용하여 변수 선언을 합니다. 하지만 변수 선언 자체를 생략할 수도 있습니다. 
 * 변수를 사용하게 됨과 동시에 메모리 할당이 일어납니다.
 */

function loginCheck() {
	if(document.frm.userid.value.length == 0) {
		alert("아이디를 써주세요");
		frm.userid.focus();
		return false;
	}
	if(document.frm.pwd.value.length == "") {
		alert("암호는 반드시 입력해야 합니다.");
		frm.pwd.focus();
		return false;
	}
	return true;
}

function idCheck() {
	if(document.frm.userid.value == "") {
		alert('아이디를 입력하여 주십시오.');
		document.frm.userid.focus();
		return;
	}
	var url = "idCheck.do?userid=" + document.frm.userid.value;
	window.open(url,"_black_1","toolbar=no,menubar=no, scrollbars=yes, resizable = no, width = 450, height = 200");
}

// 아이디 중복 체크 완료 처리를 위한 자바스크립트 함수
function idok() {
	// 자바스크립트에서 opener란 이 창을 열어준 부모 창을 말합니다. 
	opener.frm.userid.value = document.frm.userid.value;
	// reid는 아이디 중복 체크 과정을 거쳤는지를 확인하기 위해서 회원 가입 폼에 만들어둔 히든 태그입니다.
	opener.frm.reid.value = document.frm.userid.value;
	// 아이디 중복 체크하는 창을 닫습니다.
	self.close();
}

// <회원 정보의 유효성을 체크하기 위한 자바스크립트 함수>
function joinCheck() {
	if(document.frm.name.value.length == 0) {
		alert("이름을 써주세요.");
		frm.name.focus();
		return false;
	}
	if(document.frm.userid.value.length < 4) {
		alert("아이디는 4글자 이상이어야 합니다.");
		frm.userid.focus();
		return false;
	}
	if(document.frm.pwd.value=="") {
		alert("암호는 반드시 입력해야 합니다.");
		frm.pwd.focus();
		return false;
	}
	if(document.frm.pwd.value != document.frm.pwd_check.value) {
		alert("암호가 일치하지 않습니다.");
		frm.pwd.focus();
		return false;
	}
	if(document.frm.reid.value.length==0) {
		alert("중복 체크를 하지 않았습니다.");
		frm.userid.focus();
		return false;
	}
	return true;
}