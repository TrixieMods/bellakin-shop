function accountLogin(form) {
    fetch("/account/login", {
        method: "POST",
        body: new FormData(form)
    })
    .then(result => {
        result.text().then(text => {
            if(result.status !== 200) {
                alert(result.status + " : " + text);
            } else {
                document.location.replace("/");
            }
        });
    })
    .catch(error => console.error('Error:', error));;
}

function accountLogout() {
    fetch("/account/logout", {
        method: "POST"
    })
    .then(result => {
        result.text().then(text => {
            if(result.status !== 200) {
                alert(result.status + " : " + text);
            } else {
                document.location.replace("/");
            }
        });
    })
    .catch(error => console.error('Error:', error));;
}

function showCookies() {

}

function hideCookies() {

}