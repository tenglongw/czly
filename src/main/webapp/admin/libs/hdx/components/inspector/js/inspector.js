define(['jquery'], function ($) {

    // 'Internal functions used by the inspector
    //===========================================
    function adjustCaretLocation(inspector, col, table) {
        var caret = $('.icon-caret-up', inspector);

        var colPos = $(col).position().left;
        $(caret).css('left', colPos + 10);

        var rowPos = $(col).position().top;

    }

    //This method saves row in allinspectors.
    function saveRowInfo(table, row) {
        var rowId = $(row).index();
        var tblIndex = getTableIdIndx(table);
        allInspectors[tblIndex][1].push(row);
    }

    //Goes through the array of inspector info . If id is in the table, returns the array index of the info for the table.
    //If it finds nothing, appends to array : Id in location 0, empty array (for row infp) in location 1 and returns the new index.
    //
    function getTableIdIndx(table) {
        var curTblId = $(table).attr('id');
        for (var i = 0; i < allInspectors.length; i++) {
            if (allInspectors[i][0] === curTblId) {
                return (i);
            }
        }
        //did not find the info, add it
        var temp = new Array();
        temp[0] = curTblId;
        temp[1] = new Array(); //This will contain ids to all inspectors for table with particular id
        var index = allInspectors.push(temp) - 1;//Index is zreo based

        return (index);

    }

    //Removes row from allInspectors.
    function removeRowInfo(table, row) {
        var tblIdx = getTableIdIndx(table);
        var requiredArr = allInspectors[tblIdx][1];
        for (var i = 0; i < requiredArr.length; i++) {
            if (requiredArr[i] === row) {
                allInspectors[tblIdx][1].pop(row);
                return;
            }
        }
    }

    //This method closes all open inspectors for a table.
    //It also removes each closed inspectors row info from allInspectors
    function closeOpenInspectors(table) {
        var tblIdx = getTableIdIndx(table);
        var requiredArr = allInspectors[tblIdx][1];
        for (var i = 0; i < requiredArr.length; i++) {
            var row = requiredArr.pop();
            table.fnClose(row);
            //Ensure closed row is no longer highlighted.
            $(row).removeClass('row_selected');
        }
    }

    //This an array of arrays.
    //First element of array is id of table (there can be multiple tables
    // second element is an array of rows with open inspectors
    var allInspectors = new Array();
    //Plugin defination
    //======================================================
    $.fn.inspector = function (tr, table, jqueryHTML, allowMultiple/*,forceInspectorSize*/) {
        return this.each(function () {
            var col = this;
            if (table.fnIsOpen(tr)) {
                //remove the information about the row with inspector & close the row i.e the inspector;
                removeRowInfo(table, tr);
                table.fnClose(tr);
            } else {
                //table styles overide the style set for inspector.
                //Do it programatically
                $('table').attr({
                    'style': 'border-collapse:separate'
                });

                // create inspector : first add the caret, then set the class
                var arrow = "<i class='icon-caret-up'></i>";
                jqueryHTML.addClass("inspector-content");
                var inspector = $(jqueryHTML).prepend(arrow);

                adjustCaretLocation(inspector, col, table);
                if (!allowMultiple) {
                    //Close all the other inspectors for that table.
                    closeOpenInspectors(table);
                }
                //First save the row in list of open inspectors.
                saveRowInfo(table, tr);
                table.fnOpen(tr, inspector, 'inspector');

            }
			
			//prevent events bubble to inspector parent element
            $(tr).next().find("td.inspector").click(function(event){
                event.stopPropagation();
            });
            // return true so that additional handlers will run
            return true;

        });
    };
    return $;
});