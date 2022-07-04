let index = {
    init: function () {
        $("#btn-save").on("click",() => { //function(){} 말고 ()=>{} this를 바인딩하기 위해서
            this.save();
        });
//        $("#btn-login").on("click",() => { //function(){} 말고 ()=>{} this를 바인딩하기 위해서
//            this.login();
//        });
    },

//    login: function () {
//        //alert('user의 save함수 호출됨');
//        let data = {
//            username: $("#username").val(),
//            password: $("#password").val()
//        }
//
//        //console.log(data);
//
//        //ajax호출시 default가 비동기 호출
//        //ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청
//        $.ajax({
//            type: "POST",
//            url: "/api/user/login",
//            data: JSON.stringify(data), //http body데이터
//            contentType: "application/json; charset=utf-8",//body데이터가 어떤 타입인지(MIME)
//            dataType: "json"//요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 문자열
//        }).done(function (res) {
//            alert("로그인이 완료되었습니다.");
////            alert(res);
//            location.href="/";
//        }).fail(function (res) {
//            alert(JSON.stringify(error));
//        });
//    },

    save: function () {
        //alert('user의 save함수 호출됨');
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }

            //console.log(data);

        //ajax호출시 default가 비동기 호출
        //ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청
            $.ajax({
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data), //http body데이터
            contentType: "application/json; charset=utf-8",//body데이터가 어떤 타입인지(MIME)
            dataType: "json"//요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 문자열
        }).done(function (res) {
            alert("회원가입이 완료되었습니다.");
//            alert(res);
            location.href="/";
        }).fail(function (res) {
            alert(JSON.stringify(error));
        });
    }
}

index.init();