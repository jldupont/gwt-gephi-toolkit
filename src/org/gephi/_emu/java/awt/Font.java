package java.awt;

public class Font {


    /*
     * Constants to be used for logical font family names.
     */

    /**
     * A String constant for the canonical family name of the
     * logical font "Dialog". It is useful in Font construction
     * to provide compile-time verification of the name.
     * @since 1.6
     */
    public static final String DIALOG = "Dialog";

    /**
     * A String constant for the canonical family name of the
     * logical font "DialogInput". It is useful in Font construction
     * to provide compile-time verification of the name.
     * @since 1.6
     */
    public static final String DIALOG_INPUT = "DialogInput";

    /**
     * A String constant for the canonical family name of the
     * logical font "SansSerif". It is useful in Font construction
     * to provide compile-time verification of the name.
     * @since 1.6
     */
    public static final String SANS_SERIF = "SansSerif";

    /**
     * A String constant for the canonical family name of the
     * logical font "Serif". It is useful in Font construction
     * to provide compile-time verification of the name.
     * @since 1.6
     */
    public static final String SERIF = "Serif";

    /**
     * A String constant for the canonical family name of the
     * logical font "Monospaced". It is useful in Font construction
     * to provide compile-time verification of the name.
     * @since 1.6
     */
    public static final String MONOSPACED = "Monospaced";

    /*
     * Constants to be used for styles. Can be combined to mix
     * styles.
     */

    /**
     * The plain style constant.
     */
    public static final int PLAIN = 0;

    /**
     * The bold style constant.  This can be combined with the other style
     * constants (except PLAIN) for mixed styles.
     */
    public static final int BOLD = 1;

    /**
     * The italicized style constant.  This can be combined with the other
     * style constants (except PLAIN) for mixed styles.
     */
    public static final int ITALIC = 2;

    /**
     * The baseline used in most Roman scripts when laying out text.
     */
    public static final int ROMAN_BASELINE = 0;

    /**
     * The baseline used in ideographic scripts like Chinese, Japanese,
     * and Korean when laying out text.
     */
    public static final int CENTER_BASELINE = 1;

    /**
     * The baseline used in Devanigiri and similar scripts when laying
     * out text.
     */
    public static final int HANGING_BASELINE = 2;

    /**
     * Identify a font resource of type TRUETYPE.
     * Used to specify a TrueType font resource to the
     * {@link #createFont} method.
     * @since 1.3
     */

    public static final int TRUETYPE_FONT = 0;

    /**
     * Identify a font resource of type TYPE1.
     * Used to specify a Type1 font resource to the
     * {@link #createFont} method.
     * @since 1.5
     */
    public static final int TYPE1_FONT = 1;

    /**
     * The logical name of this <code>Font</code>, as passed to the
     * constructor.
     * @since JDK1.0
     *
     * @serial
     * @see #getName
     */
    protected String name;

    /**
     * The style of this <code>Font</code>, as passed to the constructor.
     * This style can be PLAIN, BOLD, ITALIC, or BOLD+ITALIC.
     * @since JDK1.0
     *
     * @serial
     * @see #getStyle()
     */
    protected int style;

    /**
     * The point size of this <code>Font</code>, rounded to integer.
     * @since JDK1.0
     *
     * @serial
     * @see #getSize()
     */
    protected int size;

    /**
     * The point size of this <code>Font</code> in <code>float</code>.
     *
     * @serial
     * @see #getSize()
     * @see #getSize2D()
     */
    protected float pointSize;

    
    public Font(String name, int style, int size) {
        this .name = (name != null) ? name : "Default";
        this .style = (style & ~0x03) == 0 ? style : 0;
        this .size = size;
        this .pointSize = size;
    }

    private Font(String name, int style, float sizePts) {
        this .name = (name != null) ? name : "Default";
        this .style = (style & ~0x03) == 0 ? style : 0;
        this .size = (int) (sizePts + 0.5);
        this .pointSize = sizePts;
    }
    
    /**
     * Returns the logical name of this <code>Font</code>.
     * Use <code>getFamily</code> to get the family name of the font.
     * Use <code>getFontName</code> to get the font face name of the font.
     * @return a <code>String</code> representing the logical name of
     *          this <code>Font</code>.
     * @see #getFamily
     * @see #getFontName
     * @since JDK1.0
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the style of this <code>Font</code>.  The style can be
     * PLAIN, BOLD, ITALIC, or BOLD+ITALIC.
     * @return the style of this <code>Font</code>
     * @see #isPlain
     * @see #isBold
     * @see #isItalic
     * @since JDK1.0
     */
    public int getStyle() {
        return style;
    }
    /**
     * Returns the point size of this <code>Font</code>, rounded to
     * an integer.
     * Most users are familiar with the idea of using <i>point size</i> to
     * specify the size of glyphs in a font. This point size defines a
     * measurement between the baseline of one line to the baseline of the
     * following line in a single spaced text document. The point size is
     * based on <i>typographic points</i>, approximately 1/72 of an inch.
     * <p>
     * The Java(tm)2D API adopts the convention that one point is
     * equivalent to one unit in user coordinates.  When using a
     * normalized transform for converting user space coordinates to
     * device space coordinates 72 user
     * space units equal 1 inch in device space.  In this case one point
     * is 1/72 of an inch.
     * @return the point size of this <code>Font</code> in 1/72 of an 
     *          inch units.
     * @see #getSize2D
     * @see GraphicsConfiguration#getDefaultTransform
     * @see GraphicsConfiguration#getNormalizingTransform
     * @since JDK1.0
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the point size of this <code>Font</code> in
     * <code>float</code> value.
     * @return the point size of this <code>Font</code> as a
     * <code>float</code> value.
     * @see #getSize
     * @since 1.2
     */
    public float getSize2D() {
        return pointSize;
    }

    /**
     * Indicates whether or not this <code>Font</code> object's style is
     * PLAIN.
     * @return    <code>true</code> if this <code>Font</code> has a
     *            PLAIN sytle;
     *            <code>false</code> otherwise.
     * @see       java.awt.Font#getStyle
     * @since     JDK1.0
     */
    public boolean isPlain() {
        return style == 0;
    }

    /**
     * Indicates whether or not this <code>Font</code> object's style is
     * BOLD.
     * @return    <code>true</code> if this <code>Font</code> object's
     *            style is BOLD;
     *            <code>false</code> otherwise.
     * @see       java.awt.Font#getStyle
     * @since     JDK1.0
     */
    public boolean isBold() {
        return (style & BOLD) != 0;
    }

    /**
     * Indicates whether or not this <code>Font</code> object's style is
     * ITALIC.
     * @return    <code>true</code> if this <code>Font</code> object's
     *            style is ITALIC;
     *            <code>false</code> otherwise.
     * @see       java.awt.Font#getStyle
     * @since     JDK1.0
     */
    public boolean isItalic() {
        return (style & ITALIC) != 0;
    }
    
}
