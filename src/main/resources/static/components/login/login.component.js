angular.module('myAdmin').controller('LoginController',LoginController);
LoginController.$inject=['adminService','$log','$state','$rootScope','$window'];
function LoginController(adminService, $log,$state) {
    var vm = this;
    vm.SendLogin = function () {
        adminService.postLogin(vm.login).then(function (response) {
            vm.demo = response.data[0].authority;
            $log.log("dem"+vm.demo);
            vm.loginSuccess(response);
        },function (response) {
            vm.loginError();
        });

    };
    vm.loginSuccess = function (success) {
        vm.admin="ROLE_Role(id=1, role=ADMIN)";
        if(vm.admin == success.data[0].authority ){
            $state.go("admin");
        }else {
            $state.go("product");
        }
        console.log("ket qua  " + success.data[0].authority);

    };
    vm.loginError = function() {
        alert('user hoặc pass sai');
    };


    $log.log(vm);
}