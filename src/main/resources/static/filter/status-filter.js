angular.module('myApp').filter('filterStatus',function () {
    var something= function (input, filterStatus) {
        var A = parseInt(input,1);
        var B = parseInt(input,0);
        var C = 'Error';
        if(isNaN(A)) {
            return 'AVAILABLE'
        } else if (isNaN(B)){
            return 'UNAVAILABLE'
        }else
            return parse.toString(C);
    }
    return something;

})