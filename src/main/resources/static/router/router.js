angular.module('myApp').config(['$stateProvider', '$urlRouterProvider',
    function config($stateProvider,$urlRouterProvider ) {
        $urlRouterProvider.otherwise('/product');
        $stateProvider
            .state('product',{
                url: '/product?page&size&search',
                reloadOnSearch: false,
                views:{
                    "viewHeader":{
                        template:"<my-header></my-header>"
                    },
                    "viewA": {
                        template: " <my-slide></my-slide>"
                    },
                    "viewB1": {
                        templateUrl: 'components/shop/shop-product.template.html',
                        controller: 'ProductShopController',
                        controllerAs: '$ctrl',

                    },
                    "viewB2":{
                        templateUrl:'components/category-product/view-narbar.template.html',
                        controller: 'CategoryProductController',
                        controllerAs: '$ctrl'
                    },
                    "viewFooter":{
                        template:"<my-footer></my-footer>"
                    }

                }
            })
            //begin quản lí admin
            .state('register',{
                url:'/register',
                reloadOnSearch: false,
                views:{
                    "viewHeader":{
                        template:"<my-header></my-header>"
                    },
                    "viewB": {
                        templateUrl:'components/admin/register.template.html',
                        controller:RegisterController,
                        controllerAs:'$ctrl'
                    },
                    "viewB2":{
                        templateUrl:'components/category-product/view-narbar.template.html',
                        controller: 'CategoryProductController',
                        controllerAs: '$ctrl'
                    },
                    "viewFooter":{
                        template:"<my-footer></my-footer>"
                    }
                }

            })
            .state('admin',{
                url:'/admin?page&size&search',
                reloadOnSearch: false,
                views:{
                    "viewHeader": {
                        template: "<header-admin></header-admin>"
                    },
                    "viewB": {
                        templateUrl:'components/admin/admin-users.template.html',
                        controller:AdminController,
                        controllerAs:'$ctrl'
                    }
                }
            })
            .state('edit',{
                url:'/edit/:id',
                reloadOnSearch: false,
                views:{
                    "viewB": {
                        templateUrl:'components/admin/edit-register.template.html',
                        controller:EditController,
                        controllerAs:'$ctrl'
                    },
                    "viewB2":{
                        templateUrl:'components/category-product/view-narbar.template.html',
                        controller: 'CategoryProductController',
                        controllerAs: '$ctrl'
                    }
                }
            })
            .state('detailCategory',{
                url: '/productCategory/:id',
                reloadOnSearch: false,
                views:{
                    "viewHeader":{
                        template:"<my-header></my-header>"
                    },
                    "viewB1": {
                        templateUrl:'components/category-product/detail-product-category.template.html',
                        controller: DetailCategoryProductController,
                        controllerAs:'$ctrl'
                    },
                    "viewB2":{
                        templateUrl:'components/category-product/view-narbar.template.html',
                        controller: 'CategoryProductController',
                        controllerAs: '$ctrl'
                    },
                    "viewFooter":{
                        template:"<my-Footer></my-Footer>"
                    }
                }
            })
            .state('sanPham',{
                url:'/sanPham',
                reloadOnSearch: false,
                views:{
                    "viewHeader":{
                        template:"<my-header></my-header>"
                    },
                    "viewB":{
                        templateUrl:'components/san-pham-product/san-pham-product.template.html',
                        controller: SanPhamProductController,
                        controllerAs:'$ctrl'
                    },
                    "viewB2":{
                        templateUrl:'components/category-product/view-narbar.template.html',
                        controller: 'CategoryProductController',
                        controllerAs: '$ctrl'
                    }
                }
            })
            // begin mapProduct
            .state('mapProduct',{
                url:'/mapProduct',
                reloadOnSearch: false,
                views:{
                    "viewB":{
                        templateUrl:'components/map-product/map-product.template.html',
                        controller: MapProductController,
                        controllerAs:'$ctrl'
                    },
                    "viewB2":{
                        templateUrl:'components/category-product/view-narbar.template.html',
                        controller: 'CategoryProductController',
                        controllerAs: '$ctrl'
                    }
                }
            })
            // begin saveProduct
            .state('saveProduct',{
                url:'/saveProduct',
                views:{

                    "viewB":{
                        templateUrl:'components/save-product/save-product.template.html',
                        controller: SaveProductController,
                        controllerAs:'$ctrl'
                    },
                    "viewB2":{
                        templateUrl:'components/category-product/view-narbar.template.html',
                        controller: 'CategoryProductController',
                        controllerAs: '$ctrl'
                    }
                }
            })
            // begin custom Order
            .state('order',{
                url:'/order',
                reloadOnSearch: false,
                views:{
                    "viewA": {
                        template: "s"
                    },
                    "viewB": {
                        templateUrl:'components/order/order.template.html',
                        controller: OrderController,
                        controllerAs:'$ctrl'
                    }
                }

            })
            // begin login
            .state('login',{
                url:'/login',
                views: {
                    "viewB": {
                        templateUrl: 'components/login/login.template.html',
                        controller: LoginController,
                        controllerAs: '$ctrl'
                    }
                }
            })
            .state('sendMail',{
                url:'/forgot-password',
                views:{
                    "viewB": {
                        templateUrl: 'components/forgot-password/send-mail/form-send-email.template.html',
                        controller: SendMailController,
                        controllerAs: '$ctrl'
                    },
                }
             })
            .state('resetPass',{
                url:'/forgot-password/reset-password?token',
                views:{
                    "viewB":{
                        templateUrl: 'components/forgot-password/reset-password/reset-password.template.html',
                        controller: ResetPasswordController,
                        controllerAs: '$ctrl'
                    }
                }
            })
            .state('profile',{
                url:'/profile',
                views:{
                    "viewHeader": {
                        template: "<header-admin></header-admin>"
                    },
                    "viewB":{
                        templateUrl:'components/profile/profile.template.html'
                    }
                }
            })
            // begin search
            // .state('search',{
            //     url:'/search',
            //     views:{
            //         "viewB":{
            //             templateUrl: "components/search-product/search-product.template.html",
            //             controller:SearchProductController,
            //             controllerAs:'$ctrl'
            //         },
            //         "viewC":{
            //             templateUrl:'components/category-product/view-narbar.template.html',
            //             controller: 'CategoryProductController',
            //             controllerAs: '$ctrl'
            //         }
            //     }
            // })
            // begin detail
            .state('detail',{
                url: '/product/:id',
                reloadOnSearch: false,
                views: {
                    "viewB1": {
                        templateUrl: 'components/detail-product/detail-product.template.html',
                        controller: ProductDetailController,
                        controllerAs: '$ctrl',
                    },
                    "viewC":{
                        templateUrl:'components/category-product/view-narbar.template.html',
                        controller: 'CategoryProductController',
                        controllerAs: '$ctrl'
                    }
                }
            })
    }
]);
