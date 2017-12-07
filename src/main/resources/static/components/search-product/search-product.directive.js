angular.module('myApp').directive('searchProduct',function () {
    return {
        restrict: 'E',
        controller: SearchProductController,
        templateUrl: 'components/search-product/search-product.template.html',
        controllerAs: '$ctrl'
    }
});