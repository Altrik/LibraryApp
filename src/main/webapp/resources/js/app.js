$(function () {
    console.log("Test");
    var testJS = $("#testJS");
    testJS.on("click", function () {
        $(this).toggleClass("test");
    });

    


    /*
    var forms = $(".searchForm");
    forms.on("submit", function (event) {
        console.log("Event done");
        event.preventDefault();
        event.stopImmediatePropagation();
        var formType = $(this).attr('id');
        console.log(formType);
        switch (formType) {
            case "formById":
                console.log("ID");
                getSearchResultListById ();
                break;
            case "formByAuthor":
                console.log("Author");
                getSearchResultListByAuthor ();
                break;
            case "formByTitle":
                console.log("Title");
                getSearchResultListByTitle ();
                break;
            case "formByDate":
                console.log("Date");
                getSearchResultListByDate ();
                break;
            case "formByStatus":
                console.log("Status");
                getSearchResultListByStatus ();
                break;
        }
    });

    var whenAjaxDone = function (object) {
        console.log("Found books");
        var anchor = $("#searchAnchor");
        anchor.empty();
        var tableHeader = $("<div id='tableHeader'><table><tr><th>ID</th><th>Title</th><th>Author</th><th>Date of Acquisition</th><th>Status</th></tr>");
        var tableEnd = $("</table></div>");
        anchor.append(tableHeader);
        object.forEach(function (element) {
            console.log("Entered the Loop");
            var elemID = JSON.stringify(element.id);
            console.log(elemID);
            var elemTitle = JSON.stringify(element.title);
            console.log(elemTitle);
            var elemAuthor = JSON.stringify(element.author);
            console.log(elemAuthor);
            var elemDate = JSON.stringify(element.dateOfAcquisition);
            console.log(elemDate);
            var elemIsBorrowed = JSON.stringify(element.isBorrowed);
            console.log(elemIsBorrowed);

            var tableContent = "<tr><td>" + elemID + "</td><td>" + elemTitle + "</td><td>" + elemAuthor + "</td><td>" + elemDate + "</td><td>" + elemIsBorrowed + "</td></tr>";
            tableHeader.append(tableContent);
        });
        tableHeader.append(tableEnd);
    }
    function getSearchResultListById () { 
        $.ajax  ({
            type: "POST",
            url: "searchById",
            datatype: "json",
            contentType: "application/json",
        })
        .done(function(object) {
            console.log("SUCCESS");
            whenAjaxDone (object)})
        .fail(function (a1, a2, a3) {
            console.log("FAIL");
            console.log(a1);
            console.log(a2);
            console.log(a3);
        })
        .always(function () {
            console.log("DONE");
        })
    }

    function getSearchResultListByTitle () {
        $.ajax  ({
            type: "POST",
            url: "searchByTitle",
            datatype: "json",
            //contentType: "application/json",
            //data: JSON.stringify({id: id, title: title, author: author, dateOfAcquisition: dateOfAcquisition, isBorrowed: isBorrowed})
        })
        .done(function(object) {
            console.log("SUCCESS");
            whenAjaxDone (object)})
        .fail(function (a1, a2, a3) {
            console.log("FAIL");
            console.log(a1);
            console.log(a2);
            console.log(a3);
        })
        .always(function () {
            console.log("DONE");
        })
    }

    function getSearchResultListByAuthor () {
        $.ajax  ({
            type: "POST",
            url: "searchByAuthor",
            datatype: "json",
            //contentType: "application/json",
            //data: JSON.stringify({id: id, title: title, author: author, dateOfAcquisition: dateOfAcquisition, isBorrowed: isBorrowed})
        })
        .done(function(object) {
            console.log("SUCCESS");
            whenAjaxDone (object)})
        .fail(function (a1, a2, a3) {
            console.log("FAIL");
            console.log(a1);
            console.log(a2);
            console.log(a3);
        })
        .always(function () {
            console.log("DONE");
        })
    }

    function getSearchResultListByDate () {
        $.ajax  ({
            type: "POST",
            url: "searchByDate",
            datatype: "json",
            //contentType: "application/json",
            //data: JSON.stringify({id: id, title: title, author: author, dateOfAcquisition: dateOfAcquisition, isBorrowed: isBorrowed})
        })
        .done(function(object) {
            console.log("SUCCESS");
            whenAjaxDone (object)})
        .fail(function (a1, a2, a3) {
            console.log("FAIL");
            console.log(a1);
            console.log(a2);
            console.log(a3);
        })
        .always(function () {
            console.log("DONE");
        })
    }

    function getSearchResultListByStatus () {
        $.ajax  ({
            type: "POST",
            url: "searchByStatus",
            datatype: "json",
            //contentType: "application/json",
            //data: JSON.stringify({id: id, title: title, author: author, dateOfAcquisition: dateOfAcquisition, isBorrowed: isBorrowed})
        })
        .done(function(object) {
            console.log("SUCCESS");
            whenAjaxDone (object)})
        .fail(function (a1, a2, a3) {
            console.log("FAIL");
            console.log(a1);
            console.log(a2);
            console.log(a3);
        })
        .always(function () {
            console.log("DONE");
        })
    }
    */
});