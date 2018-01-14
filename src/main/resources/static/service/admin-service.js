angular.module('myAdmin').factory('adminService', adminService);
adminService.$inject = ['$log', '$http'];
function adminService($log, $http) {
    var vm = this;
    vm.dataLogin = 'http://localhost:8080/login';
    vm.dataUser = '/api/users/';
    vm.dataRegister = '/api/registers/';
    vm.dataForgotPassword ='/api/forgotPassword/'

    return {
        getUsers: getUsers,
        getAll: getAll,
        postLogin: postLogin,
        postRegister: postRegister,
        deleteRegisters: deleteRegisters,
        getByIdRegister: getByIdRegister,
        editRegisters : editRegisters,
        postSendMail: postSendMail,
        postResetPassword: postResetPassword,
        postByToken:postByToken
        

    };
    function getAll() {
        return $http.get(vm.dataUser);
    }

    function getUsers(filter) {
        var _SEARCH_URL = '/api/users?page=' + (filter.page - 1) + '&size=' + filter.size;

        if (filter.search && filter.search.length) {
            _SEARCH_URL = _SEARCH_URL + '&username=' + filter.search + '&fullName=' + filter.search;
        }
        return $http.get(_SEARCH_URL).then(function (resp) {
            return resp.data;
        });
    }

    function postLogin(login) {

        return $http.post(vm.dataLogin + '?username=' + login.username + '&password=' + login.password, "", {
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
    }

    function postRegister(createRegister) {
        return $http.post(vm.dataRegister, createRegister, "", {
            headers: {'Content-Type': 'application/json'}
        });
    }
    function deleteRegisters(id) {
        return $http.delete(vm.dataUser + id);
    }

    function getByIdRegister(id) {
        return $http.get(vm.dataUser + id);
    }

    function editRegisters(id, postData) {
        return $http.post(vm.dataUser + id, postData);
    }
    function postSendMail(email) {
        return $http.post(vm.dataForgotPassword+'sendMail',email,"",{
            headers: {'Content-Type': 'application/json'}
        });
    }
    function postByToken(token) {
        return $http.post(vm.dataForgotPassword+'confirm',token,"",{
            headers: {'Content-Type': 'application/json'}
        });
    }
    function postResetPassword(token,updatePass) {
        return $http.post(vm.dataForgotPassword+'reset-password'+'?token='+token,updatePass);
    }

}
