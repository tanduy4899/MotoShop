angular.module('myApp').directive('searchProduct',function () {
    return {
        restrict: 'E',
        controller: ProductShopController,
        templateUrl: 'components/shop/shop-product.template.html',
        controllerAs: '$ctrl'
    }
});