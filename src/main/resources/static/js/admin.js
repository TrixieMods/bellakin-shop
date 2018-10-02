// Users
function addUser(form) {
    fetch("/admin/addUser", {
        method: "POST",
        body: new FormData(form)
    })
    .then(result => {
        result.text().then(text => {
            if(result.status !== 200) {
                alert(result.status + " : " + text);
            } else {
                location.reload();
            }
        });
    })
    .catch(error => console.error('Error:', error));;
}

function deleteUser(id) {
    if(!confirm("Are you sure?")) {
        return;
    }
    fetch("/admin/deleteUser", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({id:id})
    })
    .then(result => {
        result.text().then(text => {
            if(result.status !== 200) {
                alert(result.status + " : " + result.statusText + "\n" + text);
            } else {
                location.reload();
            }
        });
    })
    .catch(error => console.error('Error:', error));;
}

// Categories
function addCategory(form) {

    fetch("/admin/addCategory", {
        method: "POST",
        body: new FormData(form)
    })
    .then(result => {
        result.text().then(text => {
            if(result.status !== 200) {
                alert(result.status + " : " + text);
            } else {
                location.reload();
            }
        });
    })
    .catch(error => console.error('Error:', error));;
}

function deleteCategory(id) {
    if(!confirm("Are you sure?")) {
        return;
    }
    fetch("/admin/deleteCategory", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({id:id})
    })
    .then(result => {
        result.text().then(text => {
            if(result.status !== 200) {
                alert(result.status + " : " + result.statusText + "\n" + text);
            } else {
                location.reload();
            }
        });
    })
    .catch(error => console.error('Error:', error));;
}