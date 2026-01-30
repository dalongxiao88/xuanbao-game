package com.tool.image.test;

public class ImageMath
{
    public static final float PI = 3.1415927f;
    public static final float HALF_PI = 1.5707964f;
    public static final float QUARTER_PI = 0.7853982f;
    public static final float TWO_PI = 6.2831855f;
    private static final float m00 = -0.5f;
    private static final float m01 = 1.5f;
    private static final float m02 = -1.5f;
    private static final float m03 = 0.5f;
    private static final float m10 = 1.0f;
    private static final float m11 = -2.5f;
    private static final float m12 = 2.0f;
    private static final float m13 = -0.5f;
    private static final float m20 = -0.5f;
    private static final float m21 = 0.0f;
    private static final float m22 = 0.5f;
    private static final float m23 = 0.0f;
    private static final float m30 = 0.0f;
    private static final float m31 = 1.0f;
    private static final float m32 = 0.0f;
    private static final float m33 = 0.0f;
    
    public static float bias(float a, float b) {
        return a / ((1.0f / b - 2.0f) * (1.0f - a) + 1.0f);
    }
    
    public static float gain(float a, float b) {
        float c = (1.0f / b - 2.0f) * (1.0f - 2.0f * a);
        return ((double)a < 0.5) ? (a / (c + 1.0f)) : ((c - a) / (c - 1.0f));
    }
    
    public static float step(float a, float x) {
        return (x < a) ? 0.0f : 1.0f;
    }
    
    public static float pulse(float a, float b, float x) {
        return (x >= a && x < b) ? 1.0f : 0.0f;
    }
    
    public static float smoothPulse(float a1, float a2, float b1, float b2, float x) {
        if (x >= a1 && x < b2) {
            if (x >= a2) {
                if (x < b1) {
                    return 1.0f;
                }
                x = (x - b1) / (b2 - b1);
                return 1.0f - x * x * (3.0f - 2.0f * x);
            }
            else {
                x = (x - a1) / (a2 - a1);
                return x * x * (3.0f - 2.0f * x);
            }
        }
        else {
            return 0.0f;
        }
    }
    
    public static float smoothStep(float a, float b, float x) {
        if (x < a) {
            return 0.0f;
        }
        if (x >= b) {
            return 1.0f;
        }
        x = (x - a) / (b - a);
        return x * x * (3.0f - 2.0f * x);
    }
    
    public static float circleUp(float x) {
        x = 1.0f - x;
        return (float)Math.sqrt((double)(1.0f - x * x));
    }
    
    public static float circleDown(float x) {
        return 1.0f - (float)Math.sqrt((double)(1.0f - x * x));
    }
    
    public static float clamp(float x, float a, float b) {
        return (x < a) ? a : ((x > b) ? b : x);
    }
    
    public static int clamp(int x, int a, int b) {
        return (x < a) ? a : ((x > b) ? b : x);
    }
    
    public static double mod(double a, double b) {
        int n = (int)(a / b);
        a -= (double)n * b;
        return (a < 0.0) ? (a + b) : a;
    }
    
    public static float mod(float a, float b) {
        int n = (int)(a / b);
        a -= (float)n * b;
        return (a < 0.0f) ? (a + b) : a;
    }
    
    public static int mod(int a, int b) {
        int n = a / b;
        a -= n * b;
        return (a < 0) ? (a + b) : a;
    }
    
    public static float triangle(float x) {
        float r = mod(x, 1.0f);
        return 2.0f * (((double)r < 0.5) ? r : (1.0f - r));
    }
    
    public static float lerp(float t, float a, float b) {
        return a + t * (b - a);
    }
    
    public static int lerp(float t, int a, int b) {
        return (int)((float)a + t * (float)(b - a));
    }
    
    public static int mixColors(float t, int rgb1, int rgb2) {
        int a1 = rgb1 >> 24 & 0xFF;
        int r1 = rgb1 >> 16 & 0xFF;
        int g1 = rgb1 >> 8 & 0xFF;
        int b1 = rgb1 & 0xFF;
        int a2 = rgb2 >> 24 & 0xFF;
        int r2 = rgb2 >> 16 & 0xFF;
        int g2 = rgb2 >> 8 & 0xFF;
        int b2 = rgb2 & 0xFF;
        a1 = lerp(t, a1, a2);
        r1 = lerp(t, r1, r2);
        g1 = lerp(t, g1, g2);
        b1 = lerp(t, b1, b2);
        return a1 << 24 | r1 << 16 | g1 << 8 | b1;
    }
    
    public static int bilinearInterpolate(float x, float y, int nw, int ne, int sw, int se) {
        int a0 = nw >> 24 & 0xFF;
        int r0 = nw >> 16 & 0xFF;
        int g0 = nw >> 8 & 0xFF;
        int b0 = nw & 0xFF;
        int a2 = ne >> 24 & 0xFF;
        int r2 = ne >> 16 & 0xFF;
        int g2 = ne >> 8 & 0xFF;
        int b2 = ne & 0xFF;
        int a3 = sw >> 24 & 0xFF;
        int r3 = sw >> 16 & 0xFF;
        int g3 = sw >> 8 & 0xFF;
        int b3 = sw & 0xFF;
        int a4 = se >> 24 & 0xFF;
        int r4 = se >> 16 & 0xFF;
        int g4 = se >> 8 & 0xFF;
        int b4 = se & 0xFF;
        float cx = 1.0f - x;
        float cy = 1.0f - y;
        float m0 = cx * (float)a0 + x * (float)a2;
        float m2 = cx * (float)a3 + x * (float)a4;
        int a5 = (int)(cy * m0 + y * m2);
        m0 = cx * (float)r0 + x * (float)r2;
        m2 = cx * (float)r3 + x * (float)r4;
        int r5 = (int)(cy * m0 + y * m2);
        m0 = cx * (float)g0 + x * (float)g2;
        m2 = cx * (float)g3 + x * (float)g4;
        int g5 = (int)(cy * m0 + y * m2);
        m0 = cx * (float)b0 + x * (float)b2;
        m2 = cx * (float)b3 + x * (float)b4;
        int b5 = (int)(cy * m0 + y * m2);
        return a5 << 24 | r5 << 16 | g5 << 8 | b5;
    }
    
    public static int brightnessNTSC(int rgb) {
        int r = rgb >> 16 & 0xFF;
        int g = rgb >> 8 & 0xFF;
        int b = rgb & 0xFF;
        return (int)((float)r * 0.299f + (float)g * 0.587f + (float)b * 0.114f);
    }
    
    public static float spline(float x, int numKnots, float[] knots) {
        int numSpans = numKnots - 3;
        if (numSpans < 1) {
            throw new IllegalArgumentException("Too few knots in spline");
        }
        x = clamp(x, 0.0f, 1.0f) * (float)numSpans;
        int span = (int)x;
        if (span > numKnots - 4) {
            span = numKnots - 4;
        }
        x -= (float)span;
        float k0 = knots[span];
        float k2 = knots[span + 1];
        float k3 = knots[span + 2];
        float k4 = knots[span + 3];
        float c3 = -0.5f * k0 + 1.5f * k2 + -1.5f * k3 + 0.5f * k4;
        float c4 = 1.0f * k0 + -2.5f * k2 + 2.0f * k3 + -0.5f * k4;
        float c5 = -0.5f * k0 + 0.0f * k2 + 0.5f * k3 + 0.0f * k4;
        float c6 = 0.0f * k0 + 1.0f * k2 + 0.0f * k3 + 0.0f * k4;
        return ((c3 * x + c4) * x + c5) * x + c6;
    }
    
    public static float spline(float x, int numKnots, int[] xknots, int[] yknots) {
        int numSpans = numKnots - 3;
        if (numSpans < 1) {
            throw new IllegalArgumentException("Too few knots in spline");
        }
        int span;
        for (span = 0; span < numSpans && (float)xknots[span + 1] <= x; ++span) {}
        if (span > numKnots - 3) {
            span = numKnots - 3;
        }
        float t = (x - (float)xknots[span]) / (float)(xknots[span + 1] - xknots[span]);
        if (--span < 0) {
            span = 0;
            t = 0.0f;
        }
        float k0 = (float)yknots[span];
        float k2 = (float)yknots[span + 1];
        float k3 = (float)yknots[span + 2];
        float k4 = (float)yknots[span + 3];
        float c3 = -0.5f * k0 + 1.5f * k2 + -1.5f * k3 + 0.5f * k4;
        float c4 = 1.0f * k0 + -2.5f * k2 + 2.0f * k3 + -0.5f * k4;
        float c5 = -0.5f * k0 + 0.0f * k2 + 0.5f * k3 + 0.0f * k4;
        float c6 = 0.0f * k0 + 1.0f * k2 + 0.0f * k3 + 0.0f * k4;
        return ((c3 * t + c4) * t + c5) * t + c6;
    }
    
    public static int colorSpline(float x, int numKnots, int[] knots) {
        int numSpans = numKnots - 3;
        if (numSpans < 1) {
            throw new IllegalArgumentException("Too few knots in spline");
        }
        x = clamp(x, 0.0f, 1.0f) * (float)numSpans;
        int span = (int)x;
        if (span > numKnots - 4) {
            span = numKnots - 4;
        }
        x -= (float)span;
        int v = 0;
        for (int i = 0; i < 4; ++i) {
            int shift = i * 8;
            float k0 = (float)(knots[span] >> shift & 0xFF);
            float k2 = (float)(knots[span + 1] >> shift & 0xFF);
            float k3 = (float)(knots[span + 2] >> shift & 0xFF);
            float k4 = (float)(knots[span + 3] >> shift & 0xFF);
            float c3 = -0.5f * k0 + 1.5f * k2 + -1.5f * k3 + 0.5f * k4;
            float c4 = 1.0f * k0 + -2.5f * k2 + 2.0f * k3 + -0.5f * k4;
            float c5 = -0.5f * k0 + 0.0f * k2 + 0.5f * k3 + 0.0f * k4;
            float c6 = 0.0f * k0 + 1.0f * k2 + 0.0f * k3 + 0.0f * k4;
            int n = (int)(((c3 * x + c4) * x + c5) * x + c6);
            if (n < 0) {
                n = 0;
            }
            else if (n > 255) {
                n = 255;
            }
            v |= n << shift;
        }
        return v;
    }
    
    public static int colorSpline(int x, int numKnots, int[] xknots, int[] yknots) {
        int numSpans = numKnots - 3;
        if (numSpans < 1) {
            throw new IllegalArgumentException("Too few knots in spline");
        }
        int span;
        for (span = 0; span < numSpans && xknots[span + 1] <= x; ++span) {}
        if (span > numKnots - 3) {
            span = numKnots - 3;
        }
        float t = (float)(x - xknots[span]) / (float)(xknots[span + 1] - xknots[span]);
        if (--span < 0) {
            span = 0;
            t = 0.0f;
        }
        int v = 0;
        for (int i = 0; i < 4; ++i) {
            int shift = i * 8;
            float k0 = (float)(yknots[span] >> shift & 0xFF);
            float k2 = (float)(yknots[span + 1] >> shift & 0xFF);
            float k3 = (float)(yknots[span + 2] >> shift & 0xFF);
            float k4 = (float)(yknots[span + 3] >> shift & 0xFF);
            float c3 = -0.5f * k0 + 1.5f * k2 + -1.5f * k3 + 0.5f * k4;
            float c4 = 1.0f * k0 + -2.5f * k2 + 2.0f * k3 + -0.5f * k4;
            float c5 = -0.5f * k0 + 0.0f * k2 + 0.5f * k3 + 0.0f * k4;
            float c6 = 0.0f * k0 + 1.0f * k2 + 0.0f * k3 + 0.0f * k4;
            int n = (int)(((c3 * t + c4) * t + c5) * t + c6);
            if (n < 0) {
                n = 0;
            }
            else if (n > 255) {
                n = 255;
            }
            v |= n << shift;
        }
        return v;
    }
    
    public static void resample(int[] source, int[] dest, int length, int offset, int stride, float[] out) {
        int destIndex = offset;
        int lastIndex = source.length;
        float[] in = new float[length + 2];
        int i = 0;
        for (int j = 0; j < length; ++j) {
            while (out[i + 1] < (float)j) {
                ++i;
            }
            in[j] = (float)i + ((float)j - out[i]) / (out[i + 1] - out[i]);
        }
        in[length] = (float)length;
        in[length + 1] = (float)length;
        float inSegment = 1.0f;
        float sizfac;
        float outSegment = sizfac = in[1];
        float bSum = 0.0f;
        float gSum = 0.0f;
        float rSum = 0.0f;
        float aSum = 0.0f;
        int rgb = source[offset];
        int a = rgb >> 24 & 0xFF;
        int r = rgb >> 16 & 0xFF;
        int g = rgb >> 8 & 0xFF;
        int b = rgb & 0xFF;
        int srcIndex = offset + stride;
        rgb = source[srcIndex];
        int nextA = rgb >> 24 & 0xFF;
        int nextR = rgb >> 16 & 0xFF;
        int nextG = rgb >> 8 & 0xFF;
        int nextB = rgb & 0xFF;
        srcIndex += stride;
        i = 1;
        while (i <= length) {
            float aIntensity = inSegment * (float)a + (1.0f - inSegment) * (float)nextA;
            float rIntensity = inSegment * (float)r + (1.0f - inSegment) * (float)nextR;
            float gIntensity = inSegment * (float)g + (1.0f - inSegment) * (float)nextG;
            float bIntensity = inSegment * (float)b + (1.0f - inSegment) * (float)nextB;
            if (inSegment < outSegment) {
                aSum += aIntensity * inSegment;
                rSum += rIntensity * inSegment;
                gSum += gIntensity * inSegment;
                bSum += bIntensity * inSegment;
                outSegment -= inSegment;
                inSegment = 1.0f;
                a = nextA;
                r = nextR;
                g = nextG;
                b = nextB;
                if (srcIndex < lastIndex) {
                    rgb = source[srcIndex];
                }
                nextA = (rgb >> 24 & 0xFF);
                nextR = (rgb >> 16 & 0xFF);
                nextG = (rgb >> 8 & 0xFF);
                nextB = (rgb & 0xFF);
                srcIndex += stride;
            }
            else {
                aSum += aIntensity * outSegment;
                rSum += rIntensity * outSegment;
                gSum += gIntensity * outSegment;
                bSum += bIntensity * outSegment;
                dest[destIndex] = ((int)Math.min(aSum / sizfac, 255.0f) << 24 | (int)Math.min(rSum / sizfac, 255.0f) << 16 | (int)Math.min(gSum / sizfac, 255.0f) << 8 | (int)Math.min(bSum / sizfac, 255.0f));
                destIndex += stride;
                bSum = 0.0f;
                gSum = 0.0f;
                rSum = 0.0f;
                aSum = 0.0f;
                inSegment -= outSegment;
                outSegment = (sizfac = in[i + 1] - in[i]);
                ++i;
            }
        }
    }
    
    public static void premultiply(int[] p, int offset, int length) {
        length += offset;
        for (int i = offset; i < length; ++i) {
            int rgb = p[i];
            int a = rgb >> 24 & 0xFF;
            int r = rgb >> 16 & 0xFF;
            int g = rgb >> 8 & 0xFF;
            int b = rgb & 0xFF;
            float f = (float)a * 0.003921569f;
            r = (int)((float)r * f);
            g = (int)((float)g * f);
            b = (int)((float)b * f);
            p[i] = (a << 24 | r << 16 | g << 8 | b);
        }
    }
    
    public static void unpremultiply(int[] p, int offset, int length) {
        length += offset;
        for (int i = offset; i < length; ++i) {
            int rgb = p[i];
            int a = rgb >> 24 & 0xFF;
            int r = rgb >> 16 & 0xFF;
            int g = rgb >> 8 & 0xFF;
            int b = rgb & 0xFF;
            if (a != 0 && a != 255) {
                float f = 255.0f / (float)a;
                r = (int)((float)r * f);
                g = (int)((float)g * f);
                b = (int)((float)b * f);
                if (r > 255) {
                    r = 255;
                }
                if (g > 255) {
                    g = 255;
                }
                if (b > 255) {
                    b = 255;
                }
                p[i] = (a << 24 | r << 16 | g << 8 | b);
            }
        }
    }
}
