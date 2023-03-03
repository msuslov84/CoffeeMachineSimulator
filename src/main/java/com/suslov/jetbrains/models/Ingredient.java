package com.suslov.jetbrains.models;

/**
 * @author Mikhail Suslov
 */
enum Ingredient {
    WATER {
        @Override
        public String remainderRepresentation(int rest) {
            return String.format("%d ml of water", rest);
        }
    },
    MILK {
        @Override
        public String remainderRepresentation(int rest) {
            return String.format("%d ml of milk", rest);
        }
    },
    BEANS {
        @Override
        public String remainderRepresentation(int rest) {
            return String.format("%d g of coffee beans", rest);
        }
    },
    CUPS {
        @Override
        public String remainderRepresentation(int rest) {
            return String.format("%d disposable cups", rest);
        }
    },
    MONEY {
        @Override
        public String remainderRepresentation(int rest) {
            return String.format("$%d of money", rest);
        }
    };

    public abstract String remainderRepresentation(int rest);
}
