package com.akash.e_learniverse_spring_boot.security.constant;

public class SecurityEnum {
    public enum FootballPlayerRole {
        ADMIN("ADMIN"),
        MANAGER("MANAGER"),
        CAPTAIN("CAPTAIN"),
        PLAYER("PLAYER");

        private String role;

        FootballPlayerRole(String role) {
            this.role = role;
        }

        public String getRoleToString() {
            return role;
        }
    }
}
