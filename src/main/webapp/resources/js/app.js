$(function () {
    console.log("Test");
    var testJS = $("#testJS");
    testJS.on("click", function () {
        $(this).toggleClass("test");
    });
    var searchButtons = $(".searchBtn");
    searchButtons.on("click", function () {
        switch ($(this).attr('id')) {
            case "searchId":
                console.log("ID");
                getSearchResultListById ();
                break;
            case "searchAuthor":
                console.log("Author");
                getSearchResultListByAuthor ();
                break;
            case "searchTitle":
                console.log("Title");
                getSearchResultListByTitle ();
                break;
            case "searchDate":
                console.log("Date");
                getSearchResultListByDate ();
                break;
            case "searchStatus":
                console.log("Status");
                getSearchResultListByStatus ();
                break;
        };
    });

    var whenAjaxDone = function (object) {
        console.log("Found books");
        var anchor = $("#searchAnchor");
        var tableHeader = $("<div id='tableHeader'><table><tr><th>ID</th><th>Title</th><th>Author</th><th>Date of Acquisition</th><th>Status</th></tr>");
        var tableEnd = $("</table></div>");
        anchor.append(tableHeader);
        object.forEach(function (element) {
            var elemID = JSON.stringify(element.id);
            var elemTitle = JSON.stringify(element.title);
            var elemAuthor = JSON.stringify(element.author);
            var elemDate = JSON.stringify(element.dateOfAcquisition);
            var elemIsBorrowed = JSON.stringify(element.isBorrowed);

            tableHeader.append($("<tr>"));

            tableHeader.append($("<td>"));
            tableHeader.append(elemID);
            tableHeader.append($("</td>"));

            tableHeader.append($("<td>"));
            tableHeader.append(elemTitle);
            tableHeader.append($("</td>"));

            tableHeader.append($("<td>"));
            tableHeader.append(elemAuthor);
            tableHeader.append($("</td>"));

            tableHeader.append($("<td>"));
            tableHeader.append(elemDate);
            tableHeader.append($("</td>"));

            tableHeader.append($("<td>"));
            tableHeader.append(elemIsBorrowed);
            tableHeader.append($("</td>"));

            tableHeader.append($("</tr>"));
        });
        tableHeader.append(tableEnd);
    }
    function getSearchResultListById () { 
        $.ajax  ({
            url: "/searchById",
            datatype: "json",
        })
        .done(function(object) {
            console.log("SUCCESS");
            whenAjaxDone (object)})
        .fail(function () {
            console.log("FAIL");
        })
        .always(function () {
            console.log("DONE");
        })
    }

    function getSearchResultListByTitle () {
        $.ajax  ({
            url: "/searchByTitle",
            datatype: "json",
        })
        .done(function(object) {
            console.log("SUCCESS");
            whenAjaxDone (object)})
        .fail(function () {
            console.log("FAIL");
        })
        .always(function () {
            console.log("DONE");
        })
    }

    function getSearchResultListByAuthor () {
        $.ajax  ({
            url: "/searchByAuthor",
            datatype: "json",
        })
        .done(function(object) {
            console.log("SUCCESS");
            whenAjaxDone (object)})
        .fail(function () {
            console.log("FAIL");
        })
        .always(function () {
            console.log("DONE");
        })
    }

    function getSearchResultListByDate () {
        $.ajax  ({
            url: "/searchByDate",
            datatype: "json",
        })
        .done(function(object) {
            console.log("SUCCESS");
            whenAjaxDone (object)})
        .fail(function () {
            console.log("FAIL");
        })
        .always(function () {
            console.log("DONE");
        })
    }

    function getSearchResultListByStatus () {
        $.ajax  ({
            url: "/searchByStatus",
            datatype: "json",
        })
        .done(function(object) {
            console.log("SUCCESS");
            whenAjaxDone (object)})
        .fail(function () {
            console.log("FAIL");
        })
        .always(function () {
            console.log("DONE");
        })
    }
});