$(document).ready(function () {
    $(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });
    $('#w-input-search').autocomplete({
        source: function (request, response) {

            $(".ui-helper-hidden-accessible").remove();
            $.ajax({
                url: "/data/users/handles",
                dataType: "json",
                data: {
                    tagName: request.term
                },
                success: function (data) {
                    //alert(data);
                    if(data.length !== 0) $("#ex").empty();
                    try {
                        response(data);

                    } catch (error) {
                    }

                }
            });

        },
        classes: {
            "ui-menu-item": "dropdown-item"
        },
        minLength: 1
    }).data("ui-autocomplete")._renderItem = function (ul, item) {

        $(".ui-helper-hidden-accessible").remove();
        ul.addClass('list-unstyled'); //Ul custom class here
        ul.attr('aria-labelledby', 'w-input-search');
        ul.appendTo(".input-append");
        $(".list-group").css('border', '1px solid #b9b9b9');

        return $("<li></li>")
            .addClass("list-unstyled border border-black row list-group-item")
            .append("<a href='#'>" + item.label + "</a>")
            .data("ui-autocomplete-item", item)
            .appendTo(ul);

    };
    $("#userSearchForm").attr("autocomplete", "off").submit(function () {

        var link = "/teams/invite/to/"+ $("#teamId").val() +"/userHandle".replace(
            "userHandle", $(this).find("#w-input-search").val()
        );
        $.ajax({
            method: "post",
            url: link,
            success: function (data) {
                //alert(data);
                $("#ex").empty().append("<div class='text-success'> Пользователь приглашен </div>");
            },
            error: function (e){

                $("#ex").empty().append("<div class='text-danger'>"+ e.responseText +"</div>");
            }
        });
        return false;
    });
});

