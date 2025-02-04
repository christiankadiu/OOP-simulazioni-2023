package a05.e1;

public class ExpressionParserFactoryImpl implements ExpressionParserFactory {

    @Override
    public ExpressionParser oneSum() {
        return new ExpressionParser() {

            boolean lastIsNumber = false;
            boolean isSum = true;
            boolean openPar = true;
            int c = 0;

            @Override
            public void acceptNumber(int n) {
                if (lastIsNumber || c >= 2) {
                    throw new IllegalStateException();
                }
                lastIsNumber = true;
                if (isSum) {
                    isSum = false;
                }
                c++;
            }

            @Override
            public void acceptSum() {
                if (!lastIsNumber || c >= 2) {
                    throw new IllegalStateException();
                }
                lastIsNumber = false;
                isSum = true;
            }

            @Override
            public void acceptOpenParen() {
                if (lastIsNumber || isSum || c >= 2) {
                    throw new IllegalStateException();
                }
                lastIsNumber = true;
                openPar = true;
            }

            @Override
            public void acceptCloseParen() {
                if (!lastIsNumber || openPar) {
                    throw new IllegalStateException();
                }
                lastIsNumber = false;
            }

            @Override
            public void close() {
                if (!lastIsNumber) {
                    throw new IllegalStateException();
                }
            }

        };
    }

    @Override
    public ExpressionParser zeroOrManySums() {
        return new ExpressionParser() {

            boolean lastIsNumber = false;
            boolean isSum = true;
            boolean openPar = true;

            @Override
            public void acceptNumber(int n) {
                if (lastIsNumber) {
                    throw new IllegalStateException();
                }
                lastIsNumber = true;
                if (isSum) {
                    isSum = false;
                }
            }

            @Override
            public void acceptSum() {
                if (!lastIsNumber) {
                    throw new IllegalStateException();
                }
                lastIsNumber = false;
                isSum = true;
            }

            @Override
            public void acceptOpenParen() {
                if (lastIsNumber || isSum) {
                    throw new IllegalStateException();
                }
                lastIsNumber = true;
                openPar = true;
            }

            @Override
            public void acceptCloseParen() {
                if (!lastIsNumber || openPar) {
                    throw new IllegalStateException();
                }
                lastIsNumber = false;
            }

            @Override
            public void close() {
                if (!lastIsNumber) {
                    throw new IllegalStateException();
                }
            }

        };
    }

    @Override
    public ExpressionParser oneLevelParens() {
        return new ExpressionParser() {

            boolean lastIsNumber = false;
            boolean lastIsPar = false;

            @Override
            public void acceptNumber(int n) {
                if (lastIsNumber) {
                    throw new IllegalStateException();
                }
                lastIsNumber = true;
            }

            @Override
            public void acceptSum() {
                if (!lastIsNumber) {
                    throw new IllegalStateException();
                }
                lastIsNumber = false;
            }

            @Override
            public void acceptOpenParen() {
                if (lastIsNumber || lastIsPar) {
                    throw new IllegalStateException();
                }
                lastIsNumber = false;
                lastIsPar = true;
            }

            @Override
            public void acceptCloseParen() {
                if (!lastIsNumber) {
                    throw new IllegalStateException();
                }
                lastIsPar = false;
            }

            @Override
            public void close() {
                if (!lastIsNumber || lastIsPar) {
                    throw new IllegalStateException();
                }
            }

        };
    }

    @Override
    public ExpressionParser manySumsWithBoxingParens() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'manySumsWithBoxingParens'");
    }

}
