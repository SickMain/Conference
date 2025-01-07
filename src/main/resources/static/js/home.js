$(document).ready(function () {


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
        var link = "/profile/userHandle".replace(
            "userHandle", $(this).find("#w-input-search").val()
        );
        $.ajax({
            url: link,
            success: function (data) {
                //alert(data);
                try {
                    if(data.status === 400){
                        //$(".input-append").append("<div>Пользователь не найден</div>")
                    }
                    else{
                        window.location = link;
                    }

                } catch (error) {

                }
            },
            error: function (){
                $("#ex").empty().append("<div class='text-danger'>Пользователь не найден</div>");
            }
        });
        return false;
    });
});

